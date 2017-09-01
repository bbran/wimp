package com.libertymutual.goforcode.wimp;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.libertymutual.goforcode.wimp.api.ActorApiController;
import com.libertymutual.goforcode.wimp.api.ActorNotFoundException;
import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.ActorWithMovies;
import com.libertymutual.goforcode.wimp.models.Award;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.services.ActorRepository;
import com.libertymutual.goforcode.wimp.services.AwardRepository;


public class ActorApiControllerTests {
	private ActorRepository actorRepo;
	private AwardRepository awardRepo;
	private ActorApiController controller;
	
	@Before
	public void setUp()	{
		actorRepo = mock(ActorRepository.class);
		awardRepo = mock(AwardRepository.class);
		controller = new ActorApiController(actorRepo, awardRepo);
	}
	
	@Test
	public void test_assignAward_returns_Actor_with_Award_assigned()	{
		//Arrange
		Actor actor = new Actor();
		Award award = new Award();
		when(actorRepo.findOne(1L)).thenReturn(actor);
		when(awardRepo.save(award)).thenReturn(award);
		
		//Act
		Actor actorReturned = controller.assignAward(award, 1L);
		
		//Assert
		assertThat(actorReturned).isSameAs(award.getActor());
		verify(actorRepo).findOne(1L);
		verify(awardRepo).save(award);
	}
	
	@Test
	public void test_update_throws_ActorNotFoundException_when_id_not_found()	{
		try	{
			Actor actor = new Actor();
			controller.update(actor, 1L);
			fail("Exception was not thrown");
		} catch (ActorNotFoundException e)	{
			
		}
	}
	
	@Test
	public void test_update_updates_and_returns_Actor_for_which_id_was_provided() throws ActorNotFoundException	{
		//Arrange
		Actor actor = new Actor();
		when(actorRepo.findOne(1L)).thenReturn(actor);
		when(actorRepo.save(actor)).thenReturn(actor);
		
		//Act
		Actor actorReturned = controller.update(actor, 1L);
		
		//Assert
		assertThat(actorReturned.getId()).isEqualTo(1L);
		assertThat(actorReturned).isSameAs(actor);
		verify(actorRepo).findOne(1L);
		verify(actorRepo).save(actor);
	}

	@Test
	public void test_getAll_returns_list_of_Actors() {
		//Arrange
		List<Actor> actors = new ArrayList<Actor>();
		actors.add(new Actor());
		actors.add(new Actor());
		
		when(actorRepo.findAll()).thenReturn(actors);
		
		//Act
		List<Actor> actorsReturned = controller.getAll();
		
		//Assert
		assertThat(actorsReturned.size()).isEqualTo(2);
		assertThat(actorsReturned.get(0)).isSameAs(actors.get(0));
		verify(actorRepo).findAll();
	}
	
	@Test
	public void test_getOne_returns_Actor_matching_id_provided() throws ActorNotFoundException	{
		//Arrange
		Actor actor = new Actor();
		List<Award> awards = new ArrayList<Award>();
		List<Movie> movies = new ArrayList<Movie>();
		actor.setActiveSinceYear(2000L);
		actor.setAwards(awards);
		actor.setMovies(movies);
		actor.setBirthDate(new Date("01/01/2000"));
		actor.setFirstName("Test");
		actor.setLastName("Testerson");
		
		when(actorRepo.findOne(1L)).thenReturn(actor);
		
		//Act
		Actor actorReturned = controller.getOne(1L);
		
		//Assert
		assertThat(actorReturned).isInstanceOf(ActorWithMovies.class);
		assertThat(actorReturned.getActiveSinceYear()).isEqualTo(actor.getActiveSinceYear());
		assertThat(actorReturned.getAwards()).isSameAs(actor.getAwards());
		assertThat(actorReturned.getBirthDate()).isEqualTo(actor.getBirthDate());
		assertThat(actorReturned.getFirstName()).isEqualTo(actor.getFirstName());
		assertThat(actorReturned.getLastName()).isEqualTo(actor.getLastName());
		assertThat(actorReturned.getMovies()).isSameAs(actor.getMovies());
		verify(actorRepo).findOne(1L);
	}
	
	@Test
	public void test_getOne_throws_ActorNotFoundException_when_Actor_not_found()	{
		try	{
			controller.getOne(1L);
			fail("Exception was not thrown");
		} catch (ActorNotFoundException e)	{
			
		}
	}
	
	@Test
	public void test_create_method_inserts_and_returns_one_actor_with_Actor_detail_provided()	{
		//Arrange
		Actor actor = new Actor();
		when(actorRepo.save(actor)).thenReturn(actor);
		
		//Act
		Actor actorReturned = controller.create(actor);
		
		//Assert
		assertThat(actorReturned).isEqualTo(actor);
		verify(actorRepo).save(actor);
	}
	
	@Test
	public void test_delete_returns_Actor_that_was_deleted()	{
		//Arrange
		Actor actor = new Actor();
		when(actorRepo.findOne(1L)).thenReturn(actor);
		
		//Act
		Actor actorReturned = controller.delete(1L);
		
		//Assert
		assertThat(actorReturned).isSameAs(actor);
		verify(actorRepo).findOne(1L);
		verify(actorRepo).delete(1L);
	}

}
