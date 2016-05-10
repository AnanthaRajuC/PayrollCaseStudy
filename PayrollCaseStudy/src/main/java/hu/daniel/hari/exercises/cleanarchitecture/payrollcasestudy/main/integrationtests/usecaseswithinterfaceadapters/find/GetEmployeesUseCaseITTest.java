package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.find;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.GetEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.request.GetEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.response.GetEmployeeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.response.GetEmployeeResponse.EmployeeForGetEmployeeResponse;

public class GetEmployeesUseCaseITTest extends AbstractFindEmployeesUseCaseITTest<GetEmployeeResponse> {
	public GetEmployeesUseCaseITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	@Override
	protected GetEmployeeResponse whenExecuteUseCase() {
		GetEmployeeUseCase useCase = useCaseFactories.getEmployeeUseCase();
		useCase.execute(new GetEmployeeRequest(employeeId));
		return useCase.getResponse();
	}

	@Override
	protected EmployeeForGetEmployeeResponse getSingleResultEmployeeItem(GetEmployeeResponse response) {
		return response.employeeForGetEmployeeResponse;
	}


}
