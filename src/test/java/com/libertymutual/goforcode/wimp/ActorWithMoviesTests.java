package com.libertymutual.goforcode.wimp;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.libertymutual.goforcode.wimp.models.ActorWithMovies;
import com.libertymutual.goforcode.wimp.models.Movie;

public class ActorWithMoviesTests {

	@Test
	public void test_return_actorWithMovies_returns_a_list_of_the_actors_movies() {
		//Arrange
		ActorWithMovies actor = new ActorWithMovies();
		List<Movie> movies = new ArrayList<Movie>();
		Movie movie = new Movie();
		movies.add(movie);
		actor.setMovies(movies);
		
		//Act
		List<Movie> moviesReturned = actor.returnActorWithMovies();
		
		
		//Assert
		assertThat(moviesReturned).isSameAs(movies);
		assertThat(moviesReturned.size()).isEqualTo(1);
		assertThat(moviesReturned.get(0)).isSameAs(movie);
		
	}

}
