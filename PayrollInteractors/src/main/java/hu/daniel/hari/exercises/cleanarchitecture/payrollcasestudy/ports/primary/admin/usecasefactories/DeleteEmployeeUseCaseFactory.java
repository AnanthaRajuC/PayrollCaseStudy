package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecasefactories;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.DeleteEmployeeUseCase;

public interface DeleteEmployeeUseCaseFactory extends UseCaseFactory {
	DeleteEmployeeUseCase deleteEmployeeUseCase();
}