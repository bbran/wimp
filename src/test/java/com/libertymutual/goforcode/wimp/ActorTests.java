package com.libertymutual.goforcode.wimp;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.libertymutual.goforcode.wimp.models.Actor;

public class ActorTests {

	@Test
	public void test_actor_getters_and_setters() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(Actor.class);
	}

}
