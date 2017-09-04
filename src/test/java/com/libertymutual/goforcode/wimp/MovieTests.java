package com.libertymutual.goforcode.wimp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Movie;

public class MovieTests {

	@Test
	public void test_movie_getters_and_setters() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(Movie.class);
	}
	
	@Test
	public void test_movie_constructor_accepts_and_sets_title_distributor_releaseDate_and_budget()	{
		//Arrange
		Movie movie;
		
		//Act
		movie = new Movie("The Shining", "New Line Cinema", new Date(01/01/1990), 1000000L);
		
		//Assert
		assertThat(movie.getTitle()).isEqualTo("The Shining");
		assertThat(movie.getDistributor()).isEqualTo("New Line Cinema");
		assertThat(movie.getReleaseDate()).isEqualTo(new Date(01/01/1990));
		assertThat(movie.getBudget()).isEqualTo(1000000L);
		
	}
	
	@Test
	public void test_addActors_creates_a_new_actor_list_if_null_and_populates_it_with_an_actor()	{
		//Arrange
		Movie movie = new Movie();
		Actor actor = new Actor();
		
		//Act
		movie.addActor(actor);
		
		//Assert
		assertThat(movie.getActors().size()).isEqualTo(1);
		assertThat(movie.getActors().get(0)).isSameAs(actor);
		
	}
	
	@Test
	public void test_addActors_adds_actor_to_existing_list()	{
		//Arrange
		Movie movie = new Movie();
		List<Actor> actors = new ArrayList<Actor>();
		movie.setActors(actors);
		Actor actor = new Actor();
		
		//Act
		movie.addActor(actor);
		
		//Assert
		assertThat(movie.getActors()).isSameAs(actors);
		assertThat(movie.getActors().size()).isEqualTo(1);
		assertThat(movie.getActors().get(0)).isSameAs(actor);
		
	}

}
