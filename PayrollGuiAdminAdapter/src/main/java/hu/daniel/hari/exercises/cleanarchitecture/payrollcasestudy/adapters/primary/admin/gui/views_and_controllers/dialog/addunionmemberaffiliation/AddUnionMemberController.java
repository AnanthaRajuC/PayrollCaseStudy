package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addunionmemberaffiliation;

import java.util.List;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;
import com.google.inject.assistedinject.Assisted;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.common.formatters.usecase.error.UseCaseExceptionsFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.events.AffiliationChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.common.validation.ValidationSingleErrorMessageModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.common.validation.field.FieldValidationErrorPresenter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.common.validation.field.FieldValidatorError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.common.validation.field.FieldValidatorException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.AbstractDialogViewController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addunionmemberaffiliation.AddUnionMemberView.AddUnionMemberViewInputModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addunionmemberaffiliation.AddUnionMemberView.AddUnionMemberViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addunionmemberaffiliation.AddUnionMemberView.AddUnionMemberViewOutputModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.GetEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.GetEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.changeemployee.affiliation.AddUnionMemberAffiliationRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.employee.affiliation.unionmember.UnionMemberIdAlreadyExistsException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.newversion.factories.AddUnionMemberAffiliationUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecasefactories.GetEmployeeUseCaseFactory;

public class AddUnionMemberController extends
	AbstractDialogViewController<AddUnionMemberView, AddUnionMemberViewListener> implements
	AddUnionMemberViewListener
{
	private EventBus eventBus;
	private int employeeId;
	private GetEmployeeUseCaseFactory getEmployeeUseCaseFactory;
	private AddUnionMemberAffiliationUseCaseFactory addUnionMemberAffiliationUseCaseFactory;

	@Inject
	public AddUnionMemberController(
			EventBus eventBus,
			GetEmployeeUseCaseFactory getEmployeeUseCaseFactory,
			AddUnionMemberAffiliationUseCaseFactory addUnionMemberAffiliationUseCaseFactory,
			@Assisted int employeeId
			) {
		super(eventBus);
		this.eventBus = eventBus;
		this.getEmployeeUseCaseFactory = getEmployeeUseCaseFactory;
		this.addUnionMemberAffiliationUseCaseFactory = addUnionMemberAffiliationUseCaseFactory;
		this.employeeId = employeeId;
	}
	
	@Override
	public void setView(AddUnionMemberView view) {
		super.setView(view);
		setDefaultsToView();
	}

	private void setDefaultsToView() {
		getView().setModel(toInputModel(getEmployeeName()));
	}

	private String getEmployeeName() {
		GetEmployeeUseCase getEmployeeUseCase = getEmployeeUseCaseFactory.getEmployeeUseCase();
		getEmployeeUseCase.execute(new GetEmployeeRequest(employeeId));
		return getEmployeeUseCase.getResponse().employeeForGetEmployeeResponse.name;
	}

	private AddUnionMemberViewInputModel toInputModel(String employeeName) {
		AddUnionMemberViewInputModel inputModel = new AddUnionMemberViewInputModel();
		inputModel.employeeName = employeeName;
		return inputModel;
	}

	@Override
	public void onAdd() {
		AddUnionMemberViewOutputModel model = getView().getModel();
		try {
			validate(model);
			addUnionMemberAffiliationUseCaseFactory.addUnionMemberAffiliationUseCase().execute(
					new AddUnionMemberAffiliationRequest(employeeId, model.unionMemberId.get(), model.weeklyDueAmount.get()));
			onAdded();
		} catch (FieldValidatorException e) {
			getView().setValidationErrorMessagesModel(new FieldValidationErrorPresenter().present(e));
		} catch (UnionMemberIdAlreadyExistsException e) {
			getView().setValidationErrorMessagesModel(new ValidationSingleErrorMessageModel(new UseCaseExceptionsFormatter().format(e)));
		} 
		
	}

	private void validate(AddUnionMemberViewOutputModel model) {
		throwIfThereAreErrors(new AddUnionMemberFieldsValidator().getErrors(model));
	}
	private void throwIfThereAreErrors(List<FieldValidatorError> fieldValidatorErrors) {
		if(!fieldValidatorErrors.isEmpty())
			throw new FieldValidatorException(fieldValidatorErrors);
	}
	
	private void onAdded() {
		eventBus.post(new AffiliationChangedEvent());
		close();
	}

	@Override
	public void onCancel() {
		close();
	}

	@Override
	protected boolean onCloseAction_shouldCloseAutomatically() {
		return true;
	}

	@Override
	protected AddUnionMemberViewListener getViewListener() {
		return this;
	}

	public static interface AddUnionMemberControllerFactory {
		AddUnionMemberController create(int employeeId);
	}

}
