package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.testutil;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;

public class TestUtils {

	public static <T> T singleResult(Collection<T> collection) {
		assertThat("result count should be 1", collection.size(), is(1));
		return collection.iterator().next();
	}

}
