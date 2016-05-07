package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee;

import java.util.List;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.msg.error.validation.usecase.AddEmployeeUseCaseValidationErrorFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.ValidationErrorMessagesModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.field.FieldValidationErrorPresenter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.field.FieldValidatorException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.common.validation.field.FieldValidatorException.FieldValidatorError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.AbstractDialogViewController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.AddEmployeeViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.CommissionedEmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel.DirectPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel.EmployeeViewModelVisitor;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel.PaymasterPaymentMethod;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.EmployeeViewModel.PaymentMethod.PaymentMethodVisitor;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.HourlyEmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.SalariedEmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.requestcreator.CommissionedRequestCreator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.requestcreator.HourlyRequestCreator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.requestcreator.SalariedRequestCreator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.validator.AddCommissionedEmployeeFieldsValidator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.validator.AddHourlyEmployeeFieldsValidator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.validator.AddSalariedEmployeeFieldsValidator;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddEmployeeUseCase.AddEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.changeemployee.paymentmethod.ChangeToAbstractPaymentMethodUseCase.ChangeToAbstractPaymentMethodUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.exception.multiple.MultipleUseCaseErrorsException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.changeemployee.paymentmethod.ChangeToDirectPaymentMethodRequest;

public class AddEmployeeController extends 
	AbstractDialogViewController<AddEmployeeView, AddEmployeeViewListener> implements 
	AddEmployeeViewListener 
{

	private AddEmployeeUseCaseFactory addEmployeeUseCaseFactory;
	private ChangeToAbstractPaymentMethodUseCaseFactory changePaymentMethodUseCaseFactory;
	private EventBus eventBus;

	@Inject
	public AddEmployeeController(
			AddEmployeeUseCaseFactory addEmployeeUseCaseFactory,
			ChangeToAbstractPaymentMethodUseCaseFactory changePaymentMethodUseCaseFactory,
			EventBus eventBus
			) {
		super(eventBus);
		this.addEmployeeUseCaseFactory = addEmployeeUseCaseFactory;
		this.changePaymentMethodUseCaseFactory = changePaymentMethodUseCaseFactory;
		this.eventBus = eventBus;
	}

	@Override
	protected AddEmployeeViewListener getViewListener() {
		return this;
	}

	@Override
	protected boolean onCloseAction_shouldCloseAutomatically() {
		return true;
	}

	@Override
	public void onAddEmployee() {
		getView().getModel().accept(new OnAddEmployeeHandlerExecutor());
	}

	@Override
	public void onCancel() {
		close();
	}

	private class OnAddEmployeeHandlerExecutor implements EmployeeViewModelVisitor {
		@Override
		public void visit(SalariedEmployeeViewModel salariedEmployeeViewModel) {
			new OnAddSalariedEmployeeHandler().onAddEmployee(salariedEmployeeViewModel);
		}

		@Override
		public void visit(HourlyEmployeeViewModel hourlyEmployeeViewModel) {
			new OnAddHourlyEmployeeHandler().onAddEmployee(hourlyEmployeeViewModel);
		}

		@Override
		public void visit(CommissionedEmployeeViewModel commissionedEmployeeViewModel) {
			new OnAddCommissionedEmployeeHandler().onAddEmployee(commissionedEmployeeViewModel);
		}
	}
	
	private abstract class OnAddEmployeeHandler<T extends EmployeeViewModel> {

		public void onAddEmployee(T model) {
			try {
				validate(model);
				executeAddEmployeeUseCase(model);
				model.paymentMethod.accept(new ExecuteChangePaymentMethodUseCaseExecutor(model.employeeId.get()));
				eventBus.post(new AddedEmployeeEvent(model.employeeId.get(), model.name));
				close();
			} catch (FieldValidatorException e) {
				getView().setModel(new FieldValidationErrorPresenter().present(e));
			} catch (MultipleUseCaseErrorsException e) {
				getView().setModel(new ValidationErrorMessagesModel(
						new AddEmployeeUseCaseValidationErrorFormatter().formatAll(e.getErrors())));
			}
		}

		private void validate(T model) {
			throwIfThereAreErrors(getValidationErrors(model));
		}
		private void throwIfThereAreErrors(List<FieldValidatorError> fieldValidatorErrors) {
			if(!fieldValidatorErrors.isEmpty())
				throw new FieldValidatorException(fieldValidatorErrors);
		}

		protected abstract List<FieldValidatorError> getValidationErrors(T model);
		protected abstract void executeAddEmployeeUseCase(T model);
		
	}
	
	private class ExecuteChangePaymentMethodUseCaseExecutor implements PaymentMethodVisitor<Void> {
		private Integer employeeId;
		public ExecuteChangePaymentMethodUseCaseExecutor(Integer employeeId) {
			this.employeeId = employeeId;
		}

		@Override
		public Void visit(PaymasterPaymentMethod paymentMethod) {
			changePaymentMethodUseCaseFactory.changeToPaymasterPaymentMethodUseCase();
			return null;
		}

		@Override
		public Void visit(DirectPaymentMethod paymentMethod) {
			changePaymentMethodUseCaseFactory.changeToDirectPaymentMethodUseCase().execute(
					new ChangeToDirectPaymentMethodRequest(employeeId, paymentMethod.accountNumber));			
			return null;
		}
	}
	
	private class OnAddSalariedEmployeeHandler extends OnAddEmployeeHandler<SalariedEmployeeViewModel> {
		@Override
		protected List<FieldValidatorError> getValidationErrors(SalariedEmployeeViewModel model) {
			return new AddSalariedEmployeeFieldsValidator().getErrors(model);
		}
		@Override
		protected void executeAddEmployeeUseCase(SalariedEmployeeViewModel model) {
			addEmployeeUseCaseFactory.addSalariedEmployeeUseCase().execute(new SalariedRequestCreator().toRequest(model));
		}
	}
	
	private class OnAddHourlyEmployeeHandler extends OnAddEmployeeHandler<HourlyEmployeeViewModel> {
		@Override
		protected List<FieldValidatorError> getValidationErrors(HourlyEmployeeViewModel model) {
			return new AddHourlyEmployeeFieldsValidator().getErrors(model);
		}

		@Override
		protected void executeAddEmployeeUseCase(HourlyEmployeeViewModel model) {
			addEmployeeUseCaseFactory.addHourlyEmployeeUseCase().execute(new HourlyRequestCreator().toRequest(model));
		}
	}
	
	private class OnAddCommissionedEmployeeHandler extends OnAddEmployeeHandler<CommissionedEmployeeViewModel> {
		@Override
		protected List<FieldValidatorError> getValidationErrors(CommissionedEmployeeViewModel model) {
			return new AddCommissionedEmployeeFieldsValidator().getErrors(model);
		}

		@Override
		protected void executeAddEmployeeUseCase(CommissionedEmployeeViewModel model) {
			addEmployeeUseCaseFactory.addCommissionedEmployeeUseCase().execute(new CommissionedRequestCreator().toRequest(model));
		}
	}
	
}
