package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.HasListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ModelConsumer;

public interface EmployeeManagerView extends HasListener<EmployeeManagerView.EmployeeManagerViewListener>,
	ModelConsumer<EmployeeManagerView.EmployeeManagerViewModel>
{

	public static interface EmployeeManagerViewListener {
		void onDeleteEmployeeAction();
		void onAddEmployeeAction();
	}
	
	public static class EmployeeManagerViewModel {
		public ButtonEnabledStates buttonsEnabledStates;
		public EmployeeManagerViewModel(ButtonEnabledStates buttonsEnabledStates) {
			this.buttonsEnabledStates = buttonsEnabledStates;
		}

		public static class ButtonEnabledStates {
			public boolean deleteEmployee;
			public boolean addTimeCard;
			public boolean addSalesReceipt;
			public boolean addServiceCharge;
		}
	}


}