package com.libertymutual.goforcode.wimp.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.wimp.models.Award;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.services.AwardRepository;

@RestController
@RequestMapping("/api/awards")
public class AwardApiController {
	private AwardRepository awardRepo;
	
	public AwardApiController(AwardRepository awardRepo)	{
		this.awardRepo = awardRepo;
		
//		awardRepo.save(new Award("Best Leading Actor", "BAFTA Film Award", 2001));
//		awardRepo.save(new Award("Best Supporting Actor", "AACTA International Award", 1977));
//		awardRepo.save(new Award("Best Actor in a Comedy", "Critics Choice Award", 2010));
	}
	
	@GetMapping("")
	public List<Award> getAll()	{
		return awardRepo.findAll();
	}
	
	@PostMapping("")
	public Award create(@RequestBody Award award)	{
		return awardRepo.save(award);
	}

}
