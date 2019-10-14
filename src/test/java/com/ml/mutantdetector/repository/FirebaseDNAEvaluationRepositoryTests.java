package com.ml.mutantdetector.repository;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ml.mutantdetector.model.DNAEvaluation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FirebaseDNAEvaluationRepositoryTests {

	@Test
	public void whenDNAEvaluationIsSaved_thenSuccessOperationIsEspected() {
		DNAEvaluationRepository repository = new FirebaseDNAEvaluationRepository();
		
		String[] dna = {"AA","BB"};
		DNAEvaluation dnaEvaluation = new DNAEvaluation(dna, true);		
		
		repository.save(dnaEvaluation);
	}
	
	@Test
	public void whenDNAEvaluationIsStored_thenStoreValueIsReturned() {
		DNAEvaluationRepository repository = new FirebaseDNAEvaluationRepository();
		
		String[] dna = {"AA","BB"};
		DNAEvaluation dnaEvaluation = new DNAEvaluation(dna, true);		
		
		repository.save(dnaEvaluation);
		
		String key = DNAEvaluation.getKey(dnaEvaluation.getDna());
		
		DNAEvaluation result;
		
		try {
			result = repository.get(key);
			assertEquals(dnaEvaluation.getDna(), result.getDna());
			assertEquals(dnaEvaluation.getIsMutant(), result.getIsMutant());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	
}
