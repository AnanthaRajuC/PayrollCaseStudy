package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.TransactionalUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.DeleteEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class DeleteEmployeeUseCase extends TransactionalUseCase<DeleteEmployeeRequest> {

	public DeleteEmployeeUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected void executeInTransaction(DeleteEmployeeRequest request) {
		employeeGateway.deleteById(request.employeeId);
	}

	public static interface DeleteEmployeeUseCaseFactory {
		DeleteEmployeeUseCase deleteEmployeeUseCase();
	}

}
