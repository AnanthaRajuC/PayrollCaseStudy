package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.payday.gross;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Collection;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.AbstractUseCaseITTest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.util.TestUtils;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.PaydayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.PaydayRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.addemployee.AddSalariedEmployeeRequest;

public class PayDayUseCase_SalariedEmployee_ITTest extends PayDayUseCase_AbstractPaymentClassificationITTest {
	private static final LocalDate LAST_DAY_OF_A_MONTH = LocalDate.of(2015, 12, 31);
	
	private static final LocalDate A_PAYDAY = LAST_DAY_OF_A_MONTH;
	private static final LocalDate NOT_A_PAYDAY = LAST_DAY_OF_A_MONTH.minusDays(5);

	private final int employeeId = 1;
	private final int monthlySalary = 1000;

	public PayDayUseCase_SalariedEmployee_ITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	@Test
	public void testPaySingleSalariedEmployee_OnPayday_ShouldCreateCorrectPayCheck() throws Exception {
		givenASalariedEmployee();
		thenPayCheckGrossAmountSumShouldBeTheSalary(whenPayDayUseCaseExecuted(getAPayday()));
	}

	private void givenASalariedEmployee() {
		useCaseFactory.addSalariedEmployeeUseCase().execute(new AddSalariedEmployeeRequest(employeeId, "", "", monthlySalary));
	}

	private void thenPayCheckGrossAmountSumShouldBeTheSalary(Collection<PayCheck> payChecks) {
		thenPayCheckGrossAmountShouldBe(payChecks, monthlySalary);
	}

	@Override
	protected void givenAnEmployee() {
		givenASalariedEmployee();
	}

	@Override
	protected LocalDate getNotAPayday() {
		return NOT_A_PAYDAY;
	}

	@Override
	protected LocalDate getAPayday() {
		return A_PAYDAY;
	}

}
