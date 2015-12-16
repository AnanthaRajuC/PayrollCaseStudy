package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.affiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.NoAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.JPAAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.JPANoAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.util.autobind.AutoBindedProxy;

@AutoBindedProxy(JPANoAffiliation.class)
public class NoAffiliationProxy extends NoAffiliation implements AffiliationProxy {

	private JPANoAffiliation jpaNoAffiliation;

	public NoAffiliationProxy(JPANoAffiliation jpaNoAffiliation) {
		this.jpaNoAffiliation = jpaNoAffiliation;
	}
	
	@Override
	public JPAAffiliation getJPAObject() {
		return jpaNoAffiliation;
	}

}
