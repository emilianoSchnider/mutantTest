package com.ml.mutantdetector.repository;

import java.util.concurrent.ExecutionException;

import com.ml.mutantdetector.model.DNAEvaluation;

public interface DNAEvaluationRepository {
	
	public void save(DNAEvaluation dnaEvaluation);
	
	public DNAEvaluation get(String key) throws InterruptedException, ExecutionException;
	
}
