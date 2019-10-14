package com.ml.mutantdetector.controller;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ml.mutantdetector.dto.VerificationStatsResponse;
import com.ml.mutantdetector.service.VerificationStatsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VerificationStatsControllerTests {

	@Test
	public void voidwhenCountersIsSet_thenValidRateIsExpected() throws Exception {
		VerificationStatsResponse verificationStatsResponse = new VerificationStatsResponse(100, 40);
		VerificationStatsService verificationStatsService = Mockito.mock(VerificationStatsService.class);
		Mockito.when(verificationStatsService.getVerificationStats()).thenReturn(verificationStatsResponse);
		
		VerificationStatsController controller = new VerificationStatsController(verificationStatsService);
		VerificationStatsResponse response = controller.getVerficationStats();
		
		assertEquals(100, response.getCount_human_dna());
		assertEquals(40, response.getCount_mutant_dna());		
		assertEquals(0.4, response.getRatio(), 0.0);		
	}
}
