package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.events.CalledNotImplementedFunctionEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.events.DeletedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.formatters.controller.msg.ConfirmMessageFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.AbstractController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.AddEmployeeUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addtimecard.AddTimeCardUI.AddTimeCardUIFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.common.confirm.ConfirmDialogUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.common.confirm.ConfirmDialogUI.ConfirmDialogListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.EmployeeManagerView.EmployeeManagerViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.EmployeeManagerView.EmployeeManagerViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.EmployeeManagerView.EmployeeManagerViewModel.ButtonEnabledStates;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.DeleteEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.GetEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.DeleteEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.EmployeeListResponse.EmployeeForEmployeeListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.AffiliationTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.paymenttype.CommissionedPaymentTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.paymenttype.HourlyPaymentTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.paymenttype.PaymentTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.paymenttype.PaymentTypeResponse.PaymentTypeResponseVisitor;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.paymenttype.SalariedPaymentTypeResponse;

public class EmployeeManagerController extends 
	AbstractController<EmployeeManagerView, EmployeeManagerViewListener> implements
	EmployeeManagerViewListener 
{

	private DeleteEmployeeUseCaseFactory deleteEmployeeUseCaseFactory;
	private EventBus eventBus;
	
	private Provider<AddEmployeeUI<?>> addEmployeeUIProvider;
	private Provider<ConfirmDialogUI> confirmDialogUIProvider;
	private AddTimeCardUIFactory addTimeCardUIFactory;
	private ObservableSelectedEployee observableSelectedEployee;
	private ConfirmMessageFormatter confirmMessageFormatter;

	@Inject
	public EmployeeManagerController(
			DeleteEmployeeUseCaseFactory deleteEmployeeUseCaseFactory, 
			GetEmployeeUseCaseFactory getEmployeeUseCaseFactory, 
			EventBus eventBus,
			Provider<AddEmployeeUI<?>> addEmployeeUIProvider,
			Provider<ConfirmDialogUI> confirmDialogUIProvider,
			AddTimeCardUIFactory addTimeCardUIFactory,
			ConfirmMessageFormatter confirmMessageFormatter
			) {
		this.deleteEmployeeUseCaseFactory = deleteEmployeeUseCaseFactory;
		this.eventBus = eventBus;
		this.addEmployeeUIProvider = addEmployeeUIProvider;
		this.confirmDialogUIProvider = confirmDialogUIProvider;
		this.addTimeCardUIFactory = addTimeCardUIFactory;
		this.confirmMessageFormatter = confirmMessageFormatter;
	}

	public void setObservableSelectedEmployee(ObservableSelectedEployee observableSelectedEployee) {
		this.observableSelectedEployee = observableSelectedEployee;
		observableSelectedEployee.addChangeListener(newValue -> {
			onSelectedEmployeeIdChanged();
		});
	}

	@Override
	protected EmployeeManagerViewListener getViewListener() {
		return this;
	}

	private void onSelectedEmployeeIdChanged() {
		updateView();
	}

	private void updateView() {
		getView().setModel(new EmployeeManagerViewPresenter().present(observableSelectedEployee.get()));
	}

	@Override
	public void onDeleteEmployeeAction() {
		EmployeeForEmployeeListResponse employee = observableSelectedEployee.get().get();
		confirmDialogUIProvider.get().show(confirmMessageFormatter.deleteEmployee(employee.name), new ConfirmDialogListener() {
			@Override
			public void onOk() {
				deleteEmployeeUseCaseFactory.deleteEmployeeUseCase().execute(new DeleteEmployeeRequest(employee.id));
				eventBus.post(new DeletedEmployeeEvent(employee.id, employee.name));
			}
		});
	}

	@Override
	public void onAddEmployeeAction() {
		addEmployeeUIProvider.get().show();
	}
	
	@Override
	public void onAddSalesReceiptAction() {
		eventBus.post(new CalledNotImplementedFunctionEvent("AddSalesReceipt"));
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onAddServiceChargeAction() {
		eventBus.post(new CalledNotImplementedFunctionEvent("AddServiceCharge"));
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onAddTimeCardAction() {
		addTimeCardUIFactory.create(observableSelectedEployee.get().get().id).show();
	}

	private static class EmployeeManagerViewPresenter {
		public EmployeeManagerViewModel present(Optional<EmployeeForEmployeeListResponse> selectedEmployee) {
			return new EmployeeManagerViewModel(presentButtonsEnabledStates(selectedEmployee));
		}
	
		private ButtonEnabledStates presentButtonsEnabledStates(Optional<EmployeeForEmployeeListResponse> selectedEmployee) {
			ButtonEnabledStates buttonsEnabledStates = new ButtonEnabledStates();
			buttonsEnabledStates.deleteEmployee = selectedEmployee.isPresent();
			selectedEmployee.ifPresent((e) -> {
				presentButtonsEnabledStatesForEmployee(buttonsEnabledStates, e);
			});
			return buttonsEnabledStates;
		}

		private void presentButtonsEnabledStatesForEmployee(ButtonEnabledStates buttonsEnabledStates, EmployeeForEmployeeListResponse employee) {
			presentButtonsEnabledForPaymentType(buttonsEnabledStates, employee.paymentTypeResponse);
			presentButtonsEnabledForAffiliationType(buttonsEnabledStates, employee.affiliationTypeResponse);
		}

		private void presentButtonsEnabledForPaymentType(ButtonEnabledStates buttonsEnabledStates, PaymentTypeResponse paymentType) {
			paymentType.accept(new PaymentTypeResponseVisitor<Void>() {
				@Override
				public Void visit(SalariedPaymentTypeResponse salariedPaymentTypeResponse) {
					return null;
				}

				@Override
				public Void visit(HourlyPaymentTypeResponse hourlyPaymentTypeResponse) {
					buttonsEnabledStates.addTimeCard = true;
					return null;
				}

				@Override
				public Void visit(CommissionedPaymentTypeResponse commissionedPaymentTypeResponse) {
					buttonsEnabledStates.addSalesReceipt = true;
					return null;
				}
			});
		}

		private void presentButtonsEnabledForAffiliationType(ButtonEnabledStates buttonsEnabledStates, AffiliationTypeResponse affiliationType) {
			switch (affiliationType) {
			case NO:
				break;
			case UNION_MEMBER:
				buttonsEnabledStates.addServiceCharge = true;
				break;
			default:
				throw new RuntimeException("notimplemented");
			}
		}
		
	}


}
