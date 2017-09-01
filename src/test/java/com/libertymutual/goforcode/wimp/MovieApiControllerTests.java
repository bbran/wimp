package com.libertymutual.goforcode.wimp;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.libertymutual.goforcode.wimp.api.MovieApiController;
import com.libertymutual.goforcode.wimp.api.MovieNotFoundException;
import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.services.ActorRepository;
import com.libertymutual.goforcode.wimp.services.MovieRepository;

public class MovieApiControllerTests {
	private MovieRepository movieRepo;
	private ActorRepository actorRepo;
	private MovieApiController controller;
	
	@Before
	public void setUp()	{
		movieRepo = mock(MovieRepository.class);
		actorRepo = mock(ActorRepository.class);
		controller = new MovieApiController(movieRepo, actorRepo);
	}
	
	@Test
	public void test_update_throws_MovieNotFoundException_when_id_not_found()	{
		try	{
			Movie movie = new Movie();
			controller.update(movie, 1L);
			fail("Exception was not thrown");
		} catch (MovieNotFoundException e) {
			
		}
	}
	
	@Test
	public void test_update_updates_and_returns_Movie_for_which_id_was_provided() throws MovieNotFoundException	{
		//Arrange
		Movie movie = new Movie();
		when(movieRepo.findOne(1L)).thenReturn(movie);
		when(movieRepo.save(movie)).thenReturn(movie);
		
		//Act
		Movie movieReturned = controller.update(movie, 1L);
		
		//Assert
		assertThat(movieReturned.getId()).isEqualTo(1L);
		assertThat(movieReturned).isSameAs(movie);
		verify(movieRepo).findOne(1L);
		verify(movieRepo).save(movie);
		
	}
	
	@Test
	public void test_associateActor_adds_Actor_to_list_on_Movie_and_returns_Movie()	{
		//Arrange
		Movie movie = new Movie();
		Actor actor = new Actor();
		actor.setId(1L);
		when(movieRepo.findOne(2L)).thenReturn(movie);
		when(actorRepo.findOne(1L)).thenReturn(actor);
		
		//Act
		Movie movieReturned = controller.associateActor(actor, 2L);
		
		//Assert
		assertThat(movieReturned.getActors()).contains(actor);
		verify(movieRepo).findOne(2L);
		verify(actorRepo).findOne(1L);
		verify(movieRepo).save(movie);
		
	}

	@Test
	public void test_delete_returns_Movie_that_was_deleted()	{
		//Arrange
		Movie movie = new Movie();
		when(movieRepo.findOne(1L)).thenReturn(movie);
		
		//Act
		Movie movieReturned = controller.delete(1L);
			
		//Assert
		assertThat(movieReturned).isSameAs(movie);
		verify(movieRepo).findOne(1L);
		verify(movieRepo).delete(1L);
	}
	
	@Test
	public void test_create_returns_Movie_that_was_created()	{
		//Arrange
		Movie movie = new Movie();
		when(movieRepo.save(movie)).thenReturn(movie);
		
		//Act
		Movie movieReturned = controller.create(movie);
		
		//Assert
		assertThat(movieReturned).isSameAs(movie);
		verify(movieRepo).save(movie);
		
	}
	
	@Test
	public void test_getOne_throws_MovieNotFoundException_when_Movie_not_found()	{
		try	{
			controller.getOne(1L);
			fail("Exception was not thrown");
		} catch (MovieNotFoundException e) {
			
		}
	}
	
	@Test
	public void test_getOne_returns_Movie_matching_id_provided() throws MovieNotFoundException	{
		//Arrange
		Movie movie = new Movie();
		when(movieRepo.findOne(1L)).thenReturn(movie);
		
		//Act
		Movie movieReturned = controller.getOne(1L);
		
		//Assert
		assertThat(movieReturned).isSameAs(movie);
		verify(movieRepo).findOne(1L);
	}
	
	@Test
	public void test_getAll_returns_list_of_Movies()	{
		//Arrange
		List<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie());
		movies.add(new Movie());
		when(movieRepo.findAll()).thenReturn(movies);
		
		//Act
		List<Movie> moviesReturned = controller.getAll();
		
		//Assert
		assertThat(moviesReturned.size()).isEqualTo(2);
		assertThat(moviesReturned.get(0)).isSameAs(movies.get(0));
		verify(movieRepo).findAll();
	}
	
}
