package com.ml.mutantdetector.service;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ml.mutantdetector.dto.DNARequest;
import com.ml.mutantdetector.model.DNAEvaluation;
import com.ml.mutantdetector.repository.DNAEvaluationRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class MutantDetectorServiceTests {

	@Autowired
	MutantDetectorService mutantDetectorService;
	
	@Test
	public void whenValidMatrix_thenNoExceptionIsExpected() {
		String[] dna = {"AAAA","TTTT","GGGG","CCCC"};
		DNARequest dnaRequest = new DNARequest();
		dnaRequest.setDna(dna);

		mutantDetectorService.validate(dnaRequest);	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenMatrixContainsInvalidLetter_thenExceptionIsExpected() {
		String[] dna = {"BBB", "NNN", "AAA"};
		DNARequest dnaRequest = new DNARequest();
		dnaRequest.setDna(dna);
		
		mutantDetectorService.validate(dnaRequest);		
	}
	
	@Test
	public void whenDNAIsMutant_thenTrueIsExpected() {
		String[] dna = {"ATTTT","CCCCA","TCTCT","AAGGC","CCCAA"};
		DNARequest dnaRequest = new DNARequest();
		dnaRequest.setDna(dna);
		
		assertTrue(mutantDetectorService.isMutant(dnaRequest));
	}
	
	@Test
	public void whenDNAEvaluationIsStored_thenStoreValueIsReturned() throws InterruptedException, ExecutionException {
		String[] dna = {"AA", "BB"};
		DNARequest dnaRequest = new DNARequest();
		dnaRequest.setDna(dna);
		DNAEvaluationRepository dnaEvaluationRepository = Mockito.mock(DNAEvaluationRepository.class);		
		Mockito.when(dnaEvaluationRepository.get("AABB")).thenReturn(new DNAEvaluation(dna, true));
		MutantDetectorService service = new MutantDetectorService(dnaEvaluationRepository);
		
		DNAEvaluation dnaEvaluation = service.getDNAEvaluation(dnaRequest);
		assertTrue(dnaEvaluation.getIsMutant());
	}	
}
