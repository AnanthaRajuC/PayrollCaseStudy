package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.changeemployee.ChangeEmployeeNameRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.changeemployee.ChangeEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.Database;

public class ChangeEmployeeNameUseCase extends ChangeEmployeeUseCase<ChangeEmployeeNameRequest> {

	public ChangeEmployeeNameUseCase(Database database) {
		super(database);
	}

	@Override
	protected void change(ChangeEmployeeNameRequest request, Employee employee) {
		employee.setName(request.newName);
	}

}
