package com.libertymutual.goforcode.wimp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.libertymutual.goforcode.wimp.models.Actor;

public class ActorTests {

	@Test
	public void test_actor_getters_and_setters() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(Actor.class);
	}
	
	@Test
	public void test_actor_constructor_accepts_and_sets_firstName_lastName_activeSinceYear_and_Birthdate()	{
		//Arrange
		Actor actor;
		
		//Act
		actor = new Actor("Shia", "LaBouf", 2000L, new Date(01/01/1990));
		
		//Assert
		assertThat(actor.getFirstName()).isEqualTo("Shia");
		assertThat(actor.getLastName()).isEqualTo("LaBouf");
		assertThat(actor.getActiveSinceYear()).isEqualTo(2000L);
		assertThat(actor.getBirthDate()).isEqualTo(new Date(01/01/1990));
		
	}

}
