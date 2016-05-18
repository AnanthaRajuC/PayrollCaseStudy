package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.requestcreator;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.AddEmployeeView.SalariedEmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.addemployee.AddSalariedEmployeeRequest;

public class SalariedRequestCreator extends RequestCreator<SalariedEmployeeViewModel, AddSalariedEmployeeRequest> {
	@Override
	protected AddSalariedEmployeeRequest toSpecificRequest(SalariedEmployeeViewModel model) {
		AddSalariedEmployeeRequest addSalariedEmployeeRequest = new AddSalariedEmployeeRequest();
		addSalariedEmployeeRequest.monthlySalary = model.monthlySalary;
		return addSalariedEmployeeRequest;
	}
}