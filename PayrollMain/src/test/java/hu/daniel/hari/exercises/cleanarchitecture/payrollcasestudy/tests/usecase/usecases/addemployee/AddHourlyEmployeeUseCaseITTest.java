package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.usecase.usecases.addemployee;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.WeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.addemployee.AddHourlyEmployeeRequest;

public class AddHourlyEmployeeUseCaseITTest extends AbstractAddEmployeeUseCaseITTest {
	
	private int hourlyWage = 15;

	private AddHourlyEmployeeRequest addHourlyEmployeeRequest = new AddHourlyEmployeeRequest(employeeId, name, address, hourlyWage);
	
	public AddHourlyEmployeeUseCaseITTest(TestDatabaseProvider testDatabaseProvider) {
		super(testDatabaseProvider);
	}

	@Override
	protected void executeUseCase() {
		useCaseFactories.addHourlyEmployeeUseCase().execute(addHourlyEmployeeRequest);
	}

	@Override
	protected Class<? extends PaymentType> getPaymentTypeClass() {
		return HourlyPaymentType.class; 
	}

	@Override
	protected Class<? extends PaymentSchedule> getPaymentScheduleClass() {
		return WeeklyPaymentSchedule.class;
	}

	@Override
	protected void doAssertEmployeeTypeSpecificFields(Employee employee) {
		assertThat(((HourlyPaymentType) employee.getPaymentType()).getHourlyWage(), is(hourlyWage));
	}

}
