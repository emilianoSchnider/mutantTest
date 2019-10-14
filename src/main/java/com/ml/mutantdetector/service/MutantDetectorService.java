package com.ml.mutantdetector.service;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.mutantdetector.dto.DNARequest;
import com.ml.mutantdetector.helper.detector.MutantDetector;
import com.ml.mutantdetector.model.DNAEvaluation;
import com.ml.mutantdetector.repository.DNAEvaluationRepository;

@Service
public class MutantDetectorService {	
	
	//@Autowired
	private DNAEvaluationRepository dnaEvaluationRepository;
	
	@Autowired
	public MutantDetectorService(DNAEvaluationRepository dnaEvaluationRepository) {
		this.dnaEvaluationRepository = dnaEvaluationRepository;
	}
	
	public void validate(DNARequest dnaRequest) throws IllegalArgumentException {
		MutantDetector mutantDetector = new MutantDetector();
		mutantDetector.validate(dnaRequest.getDna());
	}
	
	public Boolean isMutant(DNARequest dnaRequest) {
		MutantDetector mutantDetector = new MutantDetector();
		
		return mutantDetector.isMutant(dnaRequest.getDna());			
	}
	
	public void saveDNARequestAndEvaluation(DNARequest dnaRequest, Boolean isMutant) {
		DNAEvaluation dnaEvaluation = new DNAEvaluation(dnaRequest.getDna(), isMutant);
		
		this.dnaEvaluationRepository.save(dnaEvaluation);		
	}
	
	public DNAEvaluation getDNAEvaluation(DNARequest dnaRequest) throws InterruptedException, ExecutionException {
		String key = DNAEvaluation.getKey(dnaRequest.getDna());
		
		return this.dnaEvaluationRepository.get(key);
	}
}
