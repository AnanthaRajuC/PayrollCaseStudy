package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.usecase.usecases.changeemployee;

import static org.junit.Assert.*;
import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.changeemployee.ChangeEmployeeNameRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.usecase.AbstractMultipleDatabaseUseCaseTest;

public class ChangeEmployeeNameUseCaseITTest extends AbstractMultipleDatabaseUseCaseTest {
	
	private int employeeId = 1;
	private String oldName = "Bela";
	private String newName = "Dorina";

	public ChangeEmployeeNameUseCaseITTest(TestDatabaseProvider testDatabaseProvider) {
		super(testDatabaseProvider);
	}
	
	@Test
	public void givenAnEmployee_whenChangingItsName_thenNameShouldBeChanged() {
		givenAnEmployee();
		whenChangingItsName();
		thenNameShouldBeChanged(database.employeeGateway().findById(employeeId));
	}


	private void givenAnEmployee() {
		useCaseFactories.addSalariedEmployeeUseCase().execute(new AddSalariedEmployeeRequest(employeeId, oldName , "", 0));
	}

	private void whenChangingItsName() {
		useCaseFactories.changeEmployeeNameUseCase().execute(new ChangeEmployeeNameRequest(employeeId, newName));
	}

	private void thenNameShouldBeChanged(Employee employee) {
		assertEquals(newName, employee.getName());
	}
}
