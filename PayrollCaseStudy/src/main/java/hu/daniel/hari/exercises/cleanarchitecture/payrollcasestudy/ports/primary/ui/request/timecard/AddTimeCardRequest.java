package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.request.timecard;

import java.time.LocalDate;

public class AddTimeCardRequest extends AbstractTimeCardRequest {
	public final LocalDate date;
	public final int workingHoursQty;

	public AddTimeCardRequest(int employeeId, LocalDate date, int workingHoursQty) {
		super(employeeId);
		this.date = date;
		this.workingHoursQty = workingHoursQty;
	}
}
