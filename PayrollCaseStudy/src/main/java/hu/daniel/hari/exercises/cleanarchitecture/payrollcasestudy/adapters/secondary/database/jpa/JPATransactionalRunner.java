package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class JPATransactionalRunner implements TransactionalRunner {
	@Inject private EntityManager entityManager;

	@Inject
	public void inited() {
		System.err.println("JPATransactionalRunner" + entityManager);
	}

	@Override
	public void executeInTransaction(Runnable runnable) {
		EntityTransaction transaction = createTransaction();
		try {
			runnable.run();
			transaction.commit();
			entityManager.clear();
		} catch (RuntimeException e) {
			transaction.rollback();
			throw e;
		}
	}
	
	private EntityTransaction createTransaction() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		return transaction;
	}

}
