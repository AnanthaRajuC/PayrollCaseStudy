package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.paymentclassification.hourly;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.hourly.JPATimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.AutoBindedProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.Proxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.TimeCard;

@AutoBindedProxy(JPATimeCard.class)
public class TimeCardProxy extends TimeCard implements Proxy<JPATimeCard> {

	private JPATimeCard jpaTimeCard;

	public TimeCardProxy(JPATimeCard jpaTimeCard) {
		this.jpaTimeCard = jpaTimeCard;
	}

	@Override
	public LocalDate getDate() {
		return jpaTimeCard.id.date;
	}
	
	@Override
	public int getWorkingHourQty() {
		return jpaTimeCard.workingHourQty;
	}
	
	@Override
	public void setDate(LocalDate date) {
		jpaTimeCard.id.date = date;
	}
	
	@Override
	public void setWorkingHourQty(int workingHourQty) {
		jpaTimeCard.workingHourQty = workingHourQty;
	}

	@Override
	public JPATimeCard getJPAObject() {
		return jpaTimeCard;
	}
	
}
