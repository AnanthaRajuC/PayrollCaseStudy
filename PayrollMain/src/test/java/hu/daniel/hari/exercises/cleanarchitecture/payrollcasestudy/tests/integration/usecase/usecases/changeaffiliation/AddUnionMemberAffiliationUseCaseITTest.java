package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.usecase.usecases.changeaffiliation;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.UnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.changeemployee.affiliation.AddUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.usecase.AbstractMultipleDatabaseUseCaseTest;

public class AddUnionMemberAffiliationUseCaseITTest extends AbstractMultipleDatabaseUseCaseTest {

	private int employeeId = 1;
	private int unionMemberId = 7000;
	private int weeklyDueAmount = 135;

	public AddUnionMemberAffiliationUseCaseITTest(TestDatabaseProvider testDatabaseProvider) {
		super(testDatabaseProvider);
	}
	
	@Test
	public void testAddUnionMemberAffiliationUseCase() {
		givenAnEmployee();
		whenAddingUnionMemberAffiliation();
		thenShouldBeUnionAffiliated(database.employeeGateway().findById(employeeId));
	}

	private void givenAnEmployee() {
		useCaseFactories.addSalariedEmployeeUseCase().execute(new AddSalariedEmployeeRequest(employeeId, "", "", 0));
	}

	private void whenAddingUnionMemberAffiliation() {
		useCaseFactories.addUnionMemberAffiliationUseCase().execute(new AddUnionMemberAffiliationRequest(employeeId, unionMemberId, weeklyDueAmount));
	}

	private void thenShouldBeUnionAffiliated(Employee employee) {
		assertThat(employee.getAffiliation(), instanceOf(UnionMemberAffiliation.class));
		assertThat(((UnionMemberAffiliation) employee.getAffiliation()).getUnionMemberId(), is(unionMemberId));
		assertThat(((UnionMemberAffiliation) employee.getAffiliation()).getWeeklyDueAmount(), is(weeklyDueAmount));
	}
}
