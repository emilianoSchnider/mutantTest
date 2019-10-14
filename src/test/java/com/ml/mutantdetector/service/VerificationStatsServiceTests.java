package com.ml.mutantdetector.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ml.mutantdetector.dto.VerificationStatsResponse;
import com.ml.mutantdetector.model.DNAVerificationCounter;
import com.ml.mutantdetector.repository.DNAVerificationCounterRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VerificationStatsServiceTests {
	
	@Test
	public void whenCountersIsSet_thenValidRateIsExpected() throws Exception {
		DNAVerificationCounter humanCounter = new DNAVerificationCounter();
		humanCounter.setCounterType("human");
		humanCounter.setCount(100);
		
		DNAVerificationCounter mutantCounter = new DNAVerificationCounter();
		mutantCounter.setCounterType("mutant");
		mutantCounter.setCount(40);
		
		DNAVerificationCounterRepository repository = Mockito.mock(DNAVerificationCounterRepository.class);
		Mockito.when(repository.getCounter("human")).thenReturn(humanCounter);
		Mockito.when(repository.getCounter("mutant")).thenReturn(mutantCounter);
		
		VerificationStatsService service = new VerificationStatsService(repository);
		
		VerificationStatsResponse verificationStatsResponse = service.getVerificationStats();
		assertEquals(humanCounter.getCount(), verificationStatsResponse.getCount_human_dna());
		assertEquals(mutantCounter.getCount(), verificationStatsResponse.getCount_mutant_dna());		
		assertEquals(0.4, verificationStatsResponse.getRatio(), 0.0);
	}
}
