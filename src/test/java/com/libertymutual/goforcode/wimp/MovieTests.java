package com.libertymutual.goforcode.wimp;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.libertymutual.goforcode.wimp.models.Movie;

public class MovieTests {

	@Test
	public void test_movie_getters_and_setters() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(Movie.class);
	}

}
