package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addemployee.paymentmethod;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel.PaymasterPaymentMethod;

public class PaymasterPaymentMethodFieldsPanel extends PaymentMethodFieldsPanel<PaymasterPaymentMethod> {

	public PaymasterPaymentMethodFieldsPanel() {
		setBorder(null);
	}
	@Override
	public PaymasterPaymentMethod getModel() {
		return new PaymasterPaymentMethod();
	}

}
