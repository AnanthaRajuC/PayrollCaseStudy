package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.affiliation.unionmember;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.affiliation.unionmember.JPAServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.Proxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.util.autobind.AutoBindedProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation.ServiceCharge;

@AutoBindedProxy(JPAServiceCharge.class)
public class ServiceChargeProxy implements ServiceCharge, Proxy<JPAServiceCharge> {

	private JPAServiceCharge jpaServiceCharge;

	public ServiceChargeProxy(JPAServiceCharge jpaServiceCharge) {
		this.jpaServiceCharge = jpaServiceCharge;
	}
	
	@Override
	public int getAmount() {
		return jpaServiceCharge.amount;
	}

	@Override
	public LocalDate getDate() {
		return jpaServiceCharge.id.date;
	}

	@Override
	public void setAmount(int amount) {
		jpaServiceCharge.amount = amount;
	}

	@Override
	public void setDate(LocalDate date) {
		jpaServiceCharge.id.date = date;
	}

	@Override
	public JPAServiceCharge getJPAObject() {
		return jpaServiceCharge;
	}

}
