package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.find;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.AbstractUseCaseITTest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddCommissionedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.Response;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.EmployeeBaseForResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.EmployeeBaseForResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.CommissionedPaymentTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.HourlyPaymentTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.PaymentTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.SalariedPaymentTypeResponse;

public abstract class AbstractFindEmployeesUseCaseITTest<T extends Response> extends AbstractUseCaseITTest {

	protected int employeeId = 1;

	private String name = "Bela";
	private String address = "address";

	abstract class Case {
		abstract void givenAnEmployee();
	}
	
	public AbstractFindEmployeesUseCaseITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}
	
	@Test
	public void givenASalariedEmployee_whenExecuteEmployeesOverview_thenResponseShouldBeOK() {
		givenThenWhen(new Case() {
			@Override
			void givenAnEmployee() {
				useCaseFactories.addSalariedEmployeeUseCase().execute(new AddSalariedEmployeeRequest(employeeId, name, address, 0));
			}
		});
	}

	@Test
	public void givenAHourlyEmployee_whenExecuteEmployeesOverview_thenResponseShouldBeOK() {
		givenThenWhen(new Case() {
			@Override
			void givenAnEmployee() {
				useCaseFactories.addHourlyEmployeeUseCase().execute(new AddHourlyEmployeeRequest(employeeId, name, address, 0));
			}
		});
	}

	@Test
	public void givenACommissionedEmployee_whenExecuteEmployeesOverview_thenResponseShouldBeOK() {
		givenThenWhen(new Case() {
			@Override
			void givenAnEmployee() {
				useCaseFactories.addCommissionedEmployeeUseCase().execute(new AddCommissionedEmployeeRequest(employeeId, name, address, 0, 0));
			}
		});
	}

	private void givenThenWhen(Case theCase) {
		theCase.givenAnEmployee();
		thenResponseShouldBeCorrect(whenExecuteUseCase(), theCase);
	}

	private void thenResponseShouldBeCorrect(T response, Case theCase) {
		assertEmployeeItem(getSingleResultEmployeeItem(response), theCase);
	}

	private void assertEmployeeItem(EmployeeBaseForResponse employeeForGetEmployeeResponse, Case theCase) {
		assertThat(employeeForGetEmployeeResponse.id, is(employeeId));
		assertThat(employeeForGetEmployeeResponse.name, is(name));
		assertThat(employeeForGetEmployeeResponse.address, is(address));
	}

	protected abstract T whenExecuteUseCase();

	protected abstract EmployeeBaseForResponse getSingleResultEmployeeItem(T response);

}