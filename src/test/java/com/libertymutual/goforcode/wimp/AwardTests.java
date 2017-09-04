package com.libertymutual.goforcode.wimp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Award;

public class AwardTests {

	@Test
	public void test_award_getters_and_setters() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(Award.class);
	}
	
	@Test
	public void test_award_constructor_accepts_and_sets_title_organization_and_year()	{
		//Arrange
		Award award;
		
		//Act
		award = new Award("ABC", "XYZ Org", 2000);
		
		//Assert
		assertThat(award.getTitle()).isEqualTo("ABC");
		assertThat(award.getOrganization()).isEqualTo("XYZ Org");
		assertThat(award.getYear()).isEqualTo(2000);
		
	}

}
