package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.inject.Singleton;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.Database;

@Singleton
public class JPADatabase implements Database {

	@Inject private JPAEntityGateway jpaEntityGateway;
	@Inject private JPATransactionalRunner jpaTransactionalRunner;
	
	@Inject private EntityManager entityManager;
	@Inject private EntityFactory entityFactory;

	@Inject
	public JPADatabase() {
	}

	@Override
	public TransactionalRunner transactionalRunner() {
		return jpaTransactionalRunner;
	}

	@Override
	public EmployeeGateway employeeGateway() {
		return jpaEntityGateway;
	}


	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public EntityFactory entityFactory() {
		return entityFactory;
	}
	
}
