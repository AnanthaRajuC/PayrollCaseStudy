package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager;

import java.util.Optional;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.DeletedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.EmployeeManagerView.EmployeeManagerViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeesTableSelectionChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.GetEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.UseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.DeleteEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.GetEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeItem;

public class EmployeeManagerController implements EmployeeManagerViewListener {

	private EmployeeManagerView view;
	private UseCaseFactory useCaseFactory;
	private EventBus eventBus;
	
	private Optional<Integer> currentSelectedEmployeeId;

	public EmployeeManagerController(EmployeeManagerView view, UseCaseFactory useCaseFactory, EventBus eventBus) {
		this.view = view;
		this.useCaseFactory = useCaseFactory;
		this.eventBus = eventBus;
		eventBus.register(this);
	}

	@Subscribe
	public void onEmployeeSelectionChanged(EmployeesTableSelectionChangedEvent e) {
		currentSelectedEmployeeId = e.employeeId;
		updateView();
	}

	private void updateView() {
		updateButtonsEnabled();
	}

	private void updateButtonsEnabled() {
		view.setButtonsEnabled(currentSelectedEmployeeId.isPresent());
	}

	@Override
	public void onDeleteAction() {
		EmployeeItem employeeItemToBeDeleted = getEmployeeItem(currentSelectedEmployeeId.get());
		useCaseFactory.deleteEmployeeUseCase().execute(new DeleteEmployeeRequest(currentSelectedEmployeeId.get()));
		eventBus.post(new DeletedEmployeeEvent(employeeItemToBeDeleted.id, employeeItemToBeDeleted.name));
	}

	private EmployeeItem getEmployeeItem(int employeeId) {
		GetEmployeeUseCase getEmployeeUseCase = useCaseFactory.getEmployeeUseCase();
		getEmployeeUseCase.execute(new GetEmployeeRequest(employeeId));
		return getEmployeeUseCase.getResponse().employeeItem;
	}

}
