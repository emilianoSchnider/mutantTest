package com.ml.mutantdetector.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ml.mutantdetector.dto.DNARequest;
import com.ml.mutantdetector.model.DNAEvaluation;
import com.ml.mutantdetector.service.MutantDetectorService;
import com.ml.mutantdetector.service.VerificationStatsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MutantDetectorControllerTests {
	
	@Test
	public void whenDNAIsMutantAndNotIsStored_thenOKIsExpected() throws Exception {
		String[] dna = {"ATTTT","CCCCA","TCTCT","AAGGC","CCCAA"};
		DNARequest dnaRequest = new DNARequest();
		dnaRequest.setDna(dna);			
		
		MutantDetectorService mutantDetectorService = Mockito.mock(MutantDetectorService.class);
		Mockito.doNothing().when(mutantDetectorService).validate(dnaRequest);		
		Mockito.when(mutantDetectorService.getDNAEvaluation(dnaRequest)).thenReturn(null);
		Mockito.doNothing().when(mutantDetectorService).saveDNARequestAndEvaluation(dnaRequest, true);
		Mockito.when(mutantDetectorService.isMutant(dnaRequest)).thenReturn(true);
		
		VerificationStatsService verificationStatsService = Mockito.mock(VerificationStatsService.class);
		Mockito.doNothing().when(verificationStatsService).incrementMutantCounter();
		Mockito.doNothing().when(verificationStatsService).incrementHumanCounter();
		
		MutantDetectorController controller = 
				new MutantDetectorController(mutantDetectorService, verificationStatsService);
		
		ResponseEntity response = controller.isMutant(dnaRequest);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void whenDNAIsMutantAndIsStored_thenOKIsExpected() throws Exception {
		String[] dna = {"ATTTT","CCCCA","TCTCT","AAGGC","CCCAA"};
		DNARequest dnaRequest = new DNARequest();
		dnaRequest.setDna(dna);			
		
		DNAEvaluation dnaEvaluation = new DNAEvaluation(dna, true);
		
		MutantDetectorService mutantDetectorService = Mockito.mock(MutantDetectorService.class);
		Mockito.doNothing().when(mutantDetectorService).validate(dnaRequest);		
		Mockito.when(mutantDetectorService.getDNAEvaluation(dnaRequest)).thenReturn(dnaEvaluation);
		
		VerificationStatsService verificationStatsService = Mockito.mock(VerificationStatsService.class);
		Mockito.doNothing().when(verificationStatsService).incrementMutantCounter();
		Mockito.doNothing().when(verificationStatsService).incrementHumanCounter();
		
		MutantDetectorController controller = 
				new MutantDetectorController(mutantDetectorService, verificationStatsService);
		
		ResponseEntity response = controller.isMutant(dnaRequest);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void whenDNANotIsMutantAndIsStored_thenFORBIDDENIsExpected() throws Exception {
		String[] dna = {"ATTTT","CCCCA","TCTCT","AAGGC","CCCAA"};
		DNARequest dnaRequest = new DNARequest();
		dnaRequest.setDna(dna);			
		
		DNAEvaluation dnaEvaluation = new DNAEvaluation(dna, false);
		
		MutantDetectorService mutantDetectorService = Mockito.mock(MutantDetectorService.class);
		Mockito.doNothing().when(mutantDetectorService).validate(dnaRequest);		
		Mockito.when(mutantDetectorService.getDNAEvaluation(dnaRequest)).thenReturn(dnaEvaluation);
		
		VerificationStatsService verificationStatsService = Mockito.mock(VerificationStatsService.class);
		Mockito.doNothing().when(verificationStatsService).incrementMutantCounter();
		Mockito.doNothing().when(verificationStatsService).incrementHumanCounter();
		
		MutantDetectorController controller = 
				new MutantDetectorController(mutantDetectorService, verificationStatsService);
		
		ResponseEntity response = controller.isMutant(dnaRequest);
		
		assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenDNAValidationDetectError_thenExceptionIsExpected() {
		String[] dna = {"ATTTT","CCCCA","TCTCT","AAGGC","CCCAA"};
		DNARequest dnaRequest = new DNARequest();
		dnaRequest.setDna(dna);	
		
		MutantDetectorService mutantDetectorService = Mockito.mock(MutantDetectorService.class);
		VerificationStatsService verificationStatsService = Mockito.mock(VerificationStatsService.class);
		Mockito.doThrow(IllegalArgumentException.class).when(mutantDetectorService).validate(dnaRequest);
		
		MutantDetectorController controller = 
				new MutantDetectorController(mutantDetectorService, verificationStatsService);
		
		controller.isMutant(dnaRequest);
	}
}
