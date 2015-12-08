package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.integrationtests;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddTimeCardRequestModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.HoldPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.BiWeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.MontlhyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.WeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddCommissionedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddHourlyEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.ChangeEmployeeNameUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.DeleteEmployeeUseCase;

public class UseCasesITTest extends AbstractDatabaseITTest {

	public UseCasesITTest(PayrollDatabase payrollDatabase) {
		super(payrollDatabase);
	}

	@After
	public void clearDatabase() {
		EntityTransaction transaction = database.getTransaction();
		database.deleteAllEmployees();
		transaction.commit();
	}

	@Test
	public void testAddSalariedEmployeeUseCase() throws Exception {
		new AddSalariedEmployeeUseCase(database, 1, "Bob", "Home", 150_000).execute();

		Employee employee = database.getEmployee(1);
		
		assertEmployee(employee, "Bob", HoldPaymentMethod.class, SalariedPaymentClassification.class, MontlhyPaymentSchedule.class);
		assertThat(((SalariedPaymentClassification) employee.getPaymentClassification()).getMonthlySalary(), is(150_000));
	}

	@Test
	public void testAddHourlyEmployeeUseCase() throws Exception {
		new AddHourlyEmployeeUseCase(database, 1, "Bob", "Home", 100).execute();
		
		Employee employee = database.getEmployee(1);
		
		assertEmployee(employee, "Bob", HoldPaymentMethod.class, HourlyPaymentClassification.class, WeeklyPaymentSchedule.class);
		assertThat(((HourlyPaymentClassification) employee.getPaymentClassification()).getHourlyWage(), is(100));
	}

	@Test
	public void testAddCommissionedEmployeeUseCase() throws Exception {
		int biWeeklyBaseSalary = 70_000;
		double commissionRate = 0.1d;
		new AddCommissionedEmployeeUseCase(database, 1, "Bob", "Home", biWeeklyBaseSalary, commissionRate).execute();
		
		Employee employee = database.getEmployee(1);
		assertEmployee(employee, "Bob", HoldPaymentMethod.class, CommissionedPaymentClassification.class, BiWeeklyPaymentSchedule.class);
		CommissionedPaymentClassification commissionedPaymentClassification = (CommissionedPaymentClassification) employee.getPaymentClassification();
		assertThat(commissionedPaymentClassification.getBiWeeklyBaseSalary(), is(70_000));
		assertThat(commissionedPaymentClassification.getCommissionRate(), is(0.1d));
	}
	
	
	@Test
	public void testDeleteEmployeeUseCase() throws Exception {
		database.addEmployee(employee());
		
		new DeleteEmployeeUseCase(database, employee().getId()).execute();
		
		assertNull(database.getEmployee(employee().getId()));
	}
	
	@Test
	public void testChangeEmployeeNameUseCase() throws Exception {
		new AddSalariedEmployeeUseCase(database, employee().getId(), employee().getName(), employee().getAddress(), 1005)
			.execute();
		
		new ChangeEmployeeNameUseCase(database, employee().getId(), "Janos")
			.execute();
		
		Employee employee = database.getEmployee(employee().getId());
		assertEquals("Janos", employee.getName());
	}
	
	@Test
	public void testAddTimeCardUseCase() throws Exception {
		new AddHourlyEmployeeUseCase(database, employee().getId(), employee().getName(), employee().getAddress(), 115)
			.execute();
		
		LocalDate date = LocalDate.of(2015, 11, 01);
		
		new AddTimeCardUseCase(database,
				new AddTimeCardRequestModel(employee().getId(), date, 8))
				.execute();
		
		Employee employee = database.getEmployee(employee().getId());
		TimeCard timeCard = singleResult(((HourlyPaymentClassification) employee.getPaymentClassification()).getTimeCardsIn(DateInterval.of(date, date)));
		assertThat(timeCard, notNullValue());
		assertEquals(8, timeCard.getWorkingHourQty());
	}
	
	private void assertEmployee(Employee employee, String name, Class<? extends PaymentMethod> paymentMethod,
			Class<? extends PaymentClassification> paymentClassification, Class<? extends PaymentSchedule> paymentSchedule) {
		assertNotNull(employee);
		assertEquals(employee.getName(), name);
		assertThat(employee.getPaymentMethod(), instanceOf(paymentMethod));
		assertThat(employee.getPaymentClassification(), instanceOf(paymentClassification));
		assertThat(employee.getPaymentSchedule(), instanceOf(paymentSchedule));
	}

	private Employee employee() {
		Employee employee = database.factory().employee();
		employee.setId(1);
		employee.setName("Boob");
		return employee;
	}

	private static <T> T singleResult(Collection<T> collection) {
		assertThat(collection.size(), is(1));
		return collection.iterator().next();
	}
	
	
}
