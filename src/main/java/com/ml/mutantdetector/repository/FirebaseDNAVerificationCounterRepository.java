package com.ml.mutantdetector.repository;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.ml.mutantdetector.configuration.AppConfiguration;
import com.ml.mutantdetector.model.DNAVerificationCounter;

@Repository
public class FirebaseDNAVerificationCounterRepository implements DNAVerificationCounterRepository {
	
	@Override
	public void incrementCounter(String counterType) {		
		DocumentReference docReference = AppConfiguration.getAppConfiguration().getDB()
				.collection("dnaVerificationCounter").document(counterType);
		docReference.update("count", FieldValue.increment(1));
	}

	@Override
	public DNAVerificationCounter getCounter(String counterType) throws InterruptedException, ExecutionException {
		ApiFuture<DocumentSnapshot> future = AppConfiguration.getAppConfiguration().getDB()
				.collection("dnaVerificationCounter").document(counterType).get();
		
		DocumentSnapshot document;
		try {
			document = future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw e;
		}
		
		if (document.exists()) {
		  return document.toObject(DNAVerificationCounter.class);
		}

		return null;
	}

}
