package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.common.formatters.controller.msg;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.common.formatters.common.SmartDateFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.globalevents.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.globalevents.AddedTimeCardEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.globalevents.AffiliationChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.globalevents.DeletedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.globalevents.PaymentsFulfilledEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.globalevents.UpdatedTimeCardEvent;

public class EventMessageFormatter {
	private SmartDateFormatter smartDateFormatter = new SmartDateFormatter();
	
	public String format(AddedEmployeeEvent event) {
		return String.format("Added new employee: %s with id %s", event.name, event.employeeId);
	}

	public String format(DeletedEmployeeEvent event) {
		return String.format("Deleted employee:(%s) %s (this can't be undone!)", event.employeeId, event.name);
	}

	public String format(AddedTimeCardEvent event) {
		return String.format("Timecard of %s has been added to %s", event.date, event.employeeName);
	}

	public String format(UpdatedTimeCardEvent event) {
		return String.format("%s's Timecard of %s has been updated", event.employeeName, event.date);
	}

	public String format(PaymentsFulfilledEvent event) {
		return String.format("Payments has been fulfilled for %s employee for pay-day %s as a total net of %s.", 
				event.employeeCount, smartDateFormatter.format(event.payDate), event.totalNetAmount);
	}

	public String format(AffiliationChangedEvent event) {
		return changesHasBeenSaved();
	}

	private String changesHasBeenSaved() {
		return String.format("Saved");
	} 
	
}
