package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.WeeklyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee.JPAPaymentSchedule;

public class WeeklyPaymentScheduleProxy extends WeeklyPaymentSchedule implements PaymentScheduleProxy {

	@Override
	public JPAPaymentSchedule getJPAPaymentSchedule() {
		return JPAPaymentSchedule.WEEKLY;
	}

}
