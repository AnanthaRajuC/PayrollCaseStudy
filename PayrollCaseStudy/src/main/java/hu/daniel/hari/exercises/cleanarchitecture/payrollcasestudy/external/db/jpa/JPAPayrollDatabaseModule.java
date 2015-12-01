package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

public class JPAPayrollDatabaseModule {
	private static final String PERSISTENCE_UNIT_NAME = "testDB";
	
	private JPAPayrollDatabase jpaPayrollDatabase;

	public JPAPayrollDatabaseModule() {
		Injector injector = Guice.createInjector(Stage.DEVELOPMENT,
				new GuiceModule(),
				new JpaPersistModule(PERSISTENCE_UNIT_NAME)
				);
		injector.getInstance(PersistService.class).start();

		jpaPayrollDatabase = injector.getInstance(JPAPayrollDatabase.class);
	}
	
	public PayrollDatabase getPayrollDatabase() {
		return jpaPayrollDatabase;
	}

}

class GuiceModule extends AbstractModule {

	public GuiceModule() {
	}
	
	@Override
	protected void configure() {
		
	}
	
}