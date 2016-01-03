package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.globalevents;

public class AddedEmployeeEvent implements EmployeeCountChangedEvent {
	public int employeeId;
	public String name;

	public AddedEmployeeEvent(int employeeId, String name) {
		this.employeeId = employeeId;
		this.name = name;
	}
	
}
