package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.usecase.usecaseswithinterfaceadapters.find;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employee.list.EmployeeListUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.EmployeeListRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.EmployeeListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.EmployeeListResponse.EmployeeForEmployeeListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.usecase.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.usecase.util.TestUtils;

public class EmployeeListUseCaseITTest extends AbstractFindEmployeesUseCaseITTest<EmployeeListResponse> {
	public EmployeeListUseCaseITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	@Override
	protected EmployeeListResponse whenExecuteUseCase() {
		return useCaseFactories.employeeListUseCase().execute(new EmployeeListRequest(LocalDate.now()));
	}

	@Override
	protected EmployeeForEmployeeListResponse getSingleResultEmployeeItem(EmployeeListResponse response) {
		return TestUtils.singleResult(response.employees);
	}

}