package com.ml.mutantdetector.service;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.mutantdetector.dto.VerificationStatsResponse;
import com.ml.mutantdetector.model.DNAVerificationCounter;
import com.ml.mutantdetector.repository.DNAVerificationCounterRepository;

@Service
public class VerificationStatsService {
	
	//@Autowired
	private DNAVerificationCounterRepository dnaVerificationCounterRepository;
	
	@Autowired
	public VerificationStatsService(DNAVerificationCounterRepository dnaVerificationCounterRepository) {
		this.dnaVerificationCounterRepository = dnaVerificationCounterRepository;
	}
	
	public void incrementMutantCounter() {
		this.dnaVerificationCounterRepository.incrementCounter("mutant");
	}
	
	public void incrementHumanCounter() {
		this.dnaVerificationCounterRepository.incrementCounter("human");
	}
	
	public VerificationStatsResponse getVerificationStats() throws InterruptedException, ExecutionException {
		DNAVerificationCounter mutantCounter = this.dnaVerificationCounterRepository.getCounter("mutant");
		DNAVerificationCounter humanCounter = this.dnaVerificationCounterRepository.getCounter("human");
		
		long mutantCount = mutantCounter == null ? 0 : mutantCounter.getCount();
		long humanCount = humanCounter == null ? 0 : humanCounter.getCount();
		
		return new VerificationStatsResponse(humanCount, mutantCount);		
	}
}
