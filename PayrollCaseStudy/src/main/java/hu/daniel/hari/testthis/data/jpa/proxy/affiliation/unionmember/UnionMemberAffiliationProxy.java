package hu.daniel.hari.testthis.data.jpa.proxy.affiliation.unionmember;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.ServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.UnionMemberAffiliation;
import hu.daniel.hari.testthis.data.jpa.dao.JPAServiceChargeDao;
import hu.daniel.hari.testthis.data.jpa.model.affiliation.JPAAffiliation;
import hu.daniel.hari.testthis.data.jpa.model.affiliation.JPAUnionMemberAffiliation;
import hu.daniel.hari.testthis.data.jpa.model.affiliation.unionmember.JPAServiceCharge;
import hu.daniel.hari.testthis.data.jpa.proxy.ProxyFactory;
import hu.daniel.hari.testthis.data.jpa.proxy.affiliation.AffiliationProxy;
import hu.daniel.hari.testthis.data.jpa.proxy.util.autobind.AutoBindedProxy;

@AutoBindedProxy(JPAUnionMemberAffiliation.class)
public class UnionMemberAffiliationProxy extends UnionMemberAffiliation implements AffiliationProxy {

	private JPAUnionMemberAffiliation jpaUnionMemberAffiliation;

	@Inject private JPAServiceChargeDao jpaServiceChargeDao;
	@Inject private ProxyFactory proxyFactory;
	
	@Inject 
	public UnionMemberAffiliationProxy(JPAUnionMemberAffiliation jpaUnionMemberAffiliation) {
		this.jpaUnionMemberAffiliation = jpaUnionMemberAffiliation;
	}

	@Override
	public int getUnionMemberId() {
		return jpaUnionMemberAffiliation.getUnionMemberId();
	}

	@Override
	public int getWeeklyDueAmount() {
		return jpaUnionMemberAffiliation.getWeeklyDueAmount();
	}

	@Override
	public void addServiceCharge(ServiceCharge serviceCharge) {
		jpaUnionMemberAffiliation.addServiceCharge(((ServiceChargeProxy) serviceCharge).getJPAObject());
	}

	@Override
	public Collection<ServiceCharge> getServiceChargesIn(DateInterval dateInterval) {
		return proxyAll(jpaServiceChargeDao.findJPAServiceChargesIn(dateInterval));
	}

	private List<ServiceCharge> proxyAll(Collection<JPAServiceCharge> jpaServiceCharges) {
		return jpaServiceCharges
				.stream()
				.map(jpaServiceCharge -> proxy(jpaServiceCharge))
				.collect(Collectors.toList());
	}

	private ServiceChargeProxy proxy(JPAServiceCharge jpaServiceCharge) {
		return proxyFactory.create(ServiceChargeProxy.class, jpaServiceCharge);
	}

	@Override
	public JPAAffiliation getJPAObject() {
		return jpaUnionMemberAffiliation;
	}

}
