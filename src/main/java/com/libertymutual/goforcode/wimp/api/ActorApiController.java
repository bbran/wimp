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
import com.libertymutual.goforcode.wimp.services.ActorRepository;

@RestController
@RequestMapping("/api/actors")
public class ActorApiController {
	private ActorRepository actorRepo;
	
	public ActorApiController(ActorRepository actorRepo)	{
		this.actorRepo = actorRepo;
		
		actorRepo.save(new Actor("Christian", "Bale", new Long(1990), new Date(Date.parse("01/01/1970"))));
		actorRepo.save(new Actor("Charlie", "Chaplin", new Long(1930), new Date(Date.parse("01/01/1900"))));
		actorRepo.save(new Actor("Kirsten", "Dunst", new Long(1995), new Date(Date.parse("01/01/1980"))));
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
		return actor;
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

}
