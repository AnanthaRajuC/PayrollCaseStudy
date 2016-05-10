package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.common.confirm;

/** This does not have a controller */
public interface ConfirmDialogUI {

	void show(String message, ConfirmDialogListener confirmDialogListener);
	
	public interface ConfirmDialogListener {
		void onOk();
	}
}
