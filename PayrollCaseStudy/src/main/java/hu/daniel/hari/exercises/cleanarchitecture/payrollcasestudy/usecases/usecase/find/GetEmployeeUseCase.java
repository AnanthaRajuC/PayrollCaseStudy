package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.find;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.TransactionalEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.GetEmployeeUseCaseRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.GetEmployeeUseCaseResponse;

public class GetEmployeeUseCase extends TransactionalEmployeeUseCase<GetEmployeeUseCaseRequest> {

	private GetEmployeeUseCaseResponse response;
	private EmployeeItemConverter employeeItemConverter = new EmployeeItemConverter();
	
	public GetEmployeeUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected void executeInTransaction(GetEmployeeUseCaseRequest request) {
		response = toResponse(employeeGateway.findById(request.employeeId));
	}

	private GetEmployeeUseCaseResponse toResponse(Employee employee) {
		return new GetEmployeeUseCaseResponse(employeeItemConverter.toEmployeeItem(employee));
	}

	public GetEmployeeUseCaseResponse getResponse() {
		return response;
	}
	
	public static interface GetEmployeeUseCaseFactory {
		GetEmployeeUseCase getEmployeeUseCase();
	}
	
}

