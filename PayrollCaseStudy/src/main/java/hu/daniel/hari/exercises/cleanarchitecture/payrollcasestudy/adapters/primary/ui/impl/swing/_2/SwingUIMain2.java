package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.ui.MainFrameUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.InMemoryDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.send.interactor.port.BankTransferPortMock;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.UseCaseFactoryImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.dev.TestDataLoader;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.UseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

public class SwingUIMain2 {
	
	public static void main(String[] args) {
		new SwingUIMain2();
	}

	public SwingUIMain2() {
		
		
		//Secondary ports
		
//		Database database = new JPADatabaseModule(JPAPersistenceUnitNames.HSQL_DB).getDatabase();
//		Database database = new JPADatabaseModule(JPAPersistenceUnitNames.POSTGRES_LOCAL_DB).getDatabase();
		Database database = new InMemoryDatabase();
		
		BankTransferPortMock bankTransferPort = new BankTransferPortMock();
		
		//Application
		UseCaseFactory useCaseFactory = new UseCaseFactoryImpl(database, bankTransferPort);
		
		//Primary ports
		new TestDataLoader().clearDatabaseAndInsertTestData(database, useCaseFactory);

		new MainFrameUI(useCaseFactory).show();
	}
	
}
