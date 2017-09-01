package com.libertymutual.goforcode.wimp.api;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.ActorWithMovies;
import com.libertymutual.goforcode.wimp.models.Award;
import com.libertymutual.goforcode.wimp.services.ActorRepository;
import com.libertymutual.goforcode.wimp.services.AwardRepository;

@RestController
@RequestMapping("/api/actors")
public class ActorApiController {
	private ActorRepository actorRepo;
	private AwardRepository awardRepo;
	
	public ActorApiController(ActorRepository actorRepo, AwardRepository awardRepo)	{
		this.actorRepo = actorRepo;
		this.awardRepo = awardRepo;
		
//		actorRepo.save(new Actor("Christian", "Bale", new Long(1990), new Date(Date.parse("01/01/1970"))));
//		actorRepo.save(new Actor("Charlie", "Chaplin", new Long(1930), new Date(Date.parse("01/01/1900"))));
//		actorRepo.save(new Actor("Kirsten", "Dunst", new Long(1995), new Date(Date.parse("01/01/1980"))));
	}
	
	@GetMapping("")
	public List<Actor> getAll()	{
		return actorRepo.findAll();
	}
	
	@GetMapping("{id}")
	public Actor getOne(@PathVariable long id) throws ActorNotFoundException	{
		Actor actor = actorRepo.findOne(id);
		if (actor == null)	{
			throw new ActorNotFoundException();
		}
		ActorWithMovies newActor = new ActorWithMovies();
		newActor.setActiveSinceYear(actor.getActiveSinceYear());
		newActor.setBirthDate(actor.getBirthDate());
		newActor.setFirstName(actor.getFirstName());
		newActor.setLastName(actor.getLastName());
		newActor.setMovies(actor.getMovies());
		newActor.setAwards(actor.getAwards());
		return newActor;
//		return actor;
	}
	
	@PostMapping("")
	public Actor create(@RequestBody Actor actor)	{
		return actorRepo.save(actor);
	}
	
	@DeleteMapping("{id}")
	public Actor delete(@PathVariable long id)	{
		Actor actor = actorRepo.findOne(id);
		actorRepo.delete(id);
		return actor;
	}
	
	@PutMapping("{id}")
	public Actor update(@RequestBody Actor actor, @PathVariable long id) throws ActorNotFoundException	{
		Actor existing = actorRepo.findOne(id);
		if (existing == null)	{
			throw new ActorNotFoundException();
		}
		actor.setId(id);
		return actorRepo.save(actor);
	}

	@PostMapping("{actorId}/awards")
	public Actor assignAward(@RequestBody Award award, @PathVariable long actorId)	{
		Actor actor = actorRepo.findOne(actorId);
		award.setActor(actor);
		awardRepo.save(award);
		return actor;
	}
}