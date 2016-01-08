package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.inject.Singleton;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

@Singleton
public class JPADatabase implements Database {

	@Inject private JPATransactionalRunner jpaTransactionalRunner;
	@Inject private JPAEntityFactory entityFactory;
	@Inject private JPAEmployeeGateway jpaEntityGateway;
	
	@Override
	public TransactionalRunner transactionalRunner() {
		return jpaTransactionalRunner;
	}

	@Override
	public EntityFactory entityFactory() {
		return entityFactory;
	}

	@Override
	public EmployeeGateway employeeGateway() {
		return jpaEntityGateway;
	}

}
