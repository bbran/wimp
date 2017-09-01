package com.libertymutual.goforcode.wimp;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.libertymutual.goforcode.wimp.api.AwardApiController;
import com.libertymutual.goforcode.wimp.models.Award;
import com.libertymutual.goforcode.wimp.services.AwardRepository;

public class AwardApiControllerTests {
	private AwardRepository awardRepo;
	private AwardApiController controller;

	@Test
	public void test_getAll_returns_list_of_awards() {
		//Arrange
		List<Award> awards = new ArrayList<Award>();
		awards.add(new Award());
		awards.add(new Award());
		when(awardRepo.findAll()).thenReturn(awards);
		
		//Act
		List<Award> awardsReturned = controller.getAll();		
		
		//Assert
		assertThat(awardsReturned.size()).isEqualTo(2);
		verify(awardRepo).findAll();
		
	}
	
	@Test
	public void test_create_returns_award_created() {
		//Arrange
		Award award = new Award();
		when(awardRepo.save(award)).thenReturn(award);
		
		//Act
		Award awardReturned = controller.create(award);
		
		//Assert
		assertThat(awardReturned).isSameAs(award);
		verify(awardRepo).save(award);
		
	}
	
	@Before
	public void setUp()	{
		awardRepo = mock(AwardRepository.class);
		controller = new AwardApiController(awardRepo);
	}

}
