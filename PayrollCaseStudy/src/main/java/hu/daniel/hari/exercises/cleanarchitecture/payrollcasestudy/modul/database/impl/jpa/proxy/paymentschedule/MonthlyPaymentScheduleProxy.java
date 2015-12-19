package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.impl.jpa.proxy.paymentschedule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.MontlhyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.impl.jpa.model.paymentschedule.JPAMonthlyPaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.impl.jpa.proxy.util.autobind.AutoBindedProxy;

@AutoBindedProxy(JPAMonthlyPaymentSchedule.class)
public class MonthlyPaymentScheduleProxy extends MontlhyPaymentSchedule implements PaymentScheduleProxy {

	private JPAMonthlyPaymentSchedule jpaPaymentSchedule;

	public MonthlyPaymentScheduleProxy(JPAMonthlyPaymentSchedule jpaPaymentSchedule) {
		this.jpaPaymentSchedule = jpaPaymentSchedule;
	}

	@Override
	public JPAMonthlyPaymentSchedule getJPAObject() {
		return jpaPaymentSchedule;
	}
	
}