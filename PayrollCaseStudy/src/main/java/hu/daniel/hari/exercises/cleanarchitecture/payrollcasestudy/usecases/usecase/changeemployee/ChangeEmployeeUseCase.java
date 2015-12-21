package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.changeemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.TransactionalUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.changeemployee.ChangeEmployeeRequest;

public abstract class ChangeEmployeeUseCase<T extends ChangeEmployeeRequest> extends TransactionalUseCase<T> {

	public ChangeEmployeeUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected final void executeInTransaction(T request) {
		change(request, employeeGateway.findById(request.employeeId));
	}

	protected abstract void change(T request, Employee employee);
	
	public static interface ChangeEmployeeUseCaseFactory {
		ChangeEmployeeNameUseCase changeEmployeeNameUseCase();
	}
	
}