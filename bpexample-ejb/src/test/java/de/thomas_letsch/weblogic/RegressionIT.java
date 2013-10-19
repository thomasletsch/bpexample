package de.thomas_letsch.weblogic;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import de.thomas_letsch.utils.RegressionTest;

@Category(RegressionTest.class)
public class RegressionIT {

	@Test
	public void slowTest() throws Exception {
		Thread.sleep(10000);
	}

}
