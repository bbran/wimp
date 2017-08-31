package com.libertymutual.goforcode.wimp.api;

import java.sql.Date;
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
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.services.ActorRepository;
import com.libertymutual.goforcode.wimp.services.MovieRepository;

@RestController
@RequestMapping("/api/movies")
public class MovieApiController {
	private MovieRepository movieRepo;
	private ActorRepository actorRepo;
	
	public MovieApiController(MovieRepository movieRepo, ActorRepository actorRepo)	{
		this.movieRepo = movieRepo;
		this.actorRepo = actorRepo;
		Movie movie1 = new Movie("The Shining", "20th Century Fox", new Date(Date.parse("01/01/1970")), new Long(230909348));
		Movie movie2 = new Movie("2001: A Space Odyssey", "20th Century Fox", new Date(Date.parse("01/01/1970")), new Long(230909348));
		movie1.setActors(actorRepo.findAll());
		movieRepo.save(movie1);
		movieRepo.save(movie2);
	}
	
	@GetMapping("")
	public List<Movie> getAll()	{
		return movieRepo.findAll();
	}
	
	@GetMapping("{id}")
	public Movie getOne(@PathVariable long id) throws MovieNotFoundException	{
		Movie movie = movieRepo.findOne(id);
		if (movie == null)	{
			throw new MovieNotFoundException();
		}
		return movie;
	}
	
	@PostMapping("")
	public Movie create(@RequestBody Movie movie)	{
		return movieRepo.save(movie);
	}
	
	@DeleteMapping("{id}")
	public Movie delete(@PathVariable long id)	{
		Movie movie = movieRepo.findOne(id);
		movieRepo.delete(id);
		return movie;
	}
	
	@PutMapping("{id}")
	public Movie update(@RequestBody Movie movie, @PathVariable long id) throws MovieNotFoundException	{
		Movie existing = movieRepo.findOne(id);
		if (existing == null)	{
			throw new MovieNotFoundException();
		}
		movie.setId(id);
		return movieRepo.save(movie);
	}
	
	@PostMapping("{movieId}/actors")
	public Movie associateActor(@RequestBody Actor actor, @PathVariable long movieId)	{
		Movie movie = movieRepo.findOne(movieId);
		movie.addActor(actorRepo.findOne(actor.getId()));
		movieRepo.save(movie);
		return movie;
	}

}
