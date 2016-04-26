package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ObservableValue;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.UI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.table.EmployeeListUI;

public abstract class EmployeeManagerUI<V extends EmployeeManagerView> extends 
	UI<V, EmployeeManagerController> 
{

	private EmployeeListUI<?> employeeListUI;
	
	public EmployeeManagerUI(
			EmployeeManagerController controller,
			V view,
			EmployeeListUI<?> employeeListUI
			) {
		super(controller, view);
		this.employeeListUI = employeeListUI;
		controller.setObservableSelectedEployeeId(employeeListUI.getObservableSelectedEployeeId());
	}

	public void setObservableCurrentDate(ObservableValue<LocalDate> observableCurrentDate) {
		employeeListUI.setObservableCurrentDate(observableCurrentDate);
	}

}
