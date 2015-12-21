package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests_old.jpa;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.JPAPayrollDatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;

public class StaticJPADatabaseTestContext {
	
	private static StaticJPADatabaseTestContext INSTANCE;

	public synchronized static StaticJPADatabaseTestContext get(String persistenceUnitName) {
		if(INSTANCE == null)
			INSTANCE = new StaticJPADatabaseTestContext(persistenceUnitName);
		return INSTANCE;
	}
	
	private Database database;

	public StaticJPADatabaseTestContext(String persistenceUnitName) {
		database = new JPAPayrollDatabaseModule(persistenceUnitName).getPayrollDatabase();
	}
	
	public Database getDatabase() {
		return database;
	}
	
	
}
