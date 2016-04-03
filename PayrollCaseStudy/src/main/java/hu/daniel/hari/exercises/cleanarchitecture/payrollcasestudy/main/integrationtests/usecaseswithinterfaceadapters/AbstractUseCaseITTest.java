package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters;

import static org.junit.Assert.fail;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.UseCaseFactoriesImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.ParameterizedMultipleDatabaseITTest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.UseCaseFactories;

public abstract class AbstractUseCaseITTest extends ParameterizedMultipleDatabaseITTest {

	protected UseCaseFactories useCaseFactories = new UseCaseFactoriesImpl(database, null);

	public AbstractUseCaseITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

}
