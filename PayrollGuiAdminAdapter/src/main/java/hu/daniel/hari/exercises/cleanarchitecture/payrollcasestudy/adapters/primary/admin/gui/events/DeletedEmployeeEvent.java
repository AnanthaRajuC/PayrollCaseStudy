package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.events;

public class DeletedEmployeeEvent implements EmployeeChangedEvent {
	public int employeeId;
	public String name;

	public DeletedEmployeeEvent(int employeeId, String name) {
		this.employeeId = employeeId;
		this.name = name;
	}
	
}
