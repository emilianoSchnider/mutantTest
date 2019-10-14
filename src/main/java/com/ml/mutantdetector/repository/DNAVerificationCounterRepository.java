package com.ml.mutantdetector.repository;

import java.util.concurrent.ExecutionException;

import com.ml.mutantdetector.model.DNAVerificationCounter;

public interface DNAVerificationCounterRepository {
	
	public void incrementCounter(String counterType);
	
	public DNAVerificationCounter getCounter(String counterType) throws InterruptedException, ExecutionException;
}
