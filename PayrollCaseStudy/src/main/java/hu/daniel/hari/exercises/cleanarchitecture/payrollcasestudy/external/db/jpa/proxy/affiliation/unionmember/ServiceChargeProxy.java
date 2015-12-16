package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.affiliation.unionmember;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.ServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.unionmember.JPAServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.Proxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.util.autobind.AutoBindedProxy;

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
