package com.libertymutual.goforcode.wimp;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.libertymutual.goforcode.wimp.models.Award;

public class AwardTests {

	@Test
	public void test_award_getters_and_setters() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(Award.class);
	}

}
