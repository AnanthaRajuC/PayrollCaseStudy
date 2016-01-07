package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.pay.generate.deductions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.pay.generate.AbstractGeneratePayUseCase_ITTest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.util.TestUtils;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.changeemployee.affiliation.AddUnionMemberAffiliationRequest;

public abstract class PayDayUseCase_AbstractUnionMemberAffiliated_ITTest extends AbstractGeneratePayUseCase_ITTest {

	private final int employeeId = 1;
	protected int unionMemberId = 7000;

	public PayDayUseCase_AbstractUnionMemberAffiliated_ITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	protected void givenASalariedEmployee_WithUnionMembershipAffiliation(int weeklyDueAmount) {
		useCaseFactory.addSalariedEmployeeUseCase().execute(new AddSalariedEmployeeRequest(employeeId, "", "", 0));
		useCaseFactory.addUnionMemberAffiliationUseCase().execute(new AddUnionMemberAffiliationRequest(employeeId, unionMemberId, weeklyDueAmount));
	}

	protected void thenPayCheckDeductionsAmount_ShouldBe(Collection<PayCheck> payChecks, int payCheckDeductionsAmount) {
		assertThat(TestUtils.singleResult(payChecks).getDeductionsAmount(), is(payCheckDeductionsAmount));
	}

}