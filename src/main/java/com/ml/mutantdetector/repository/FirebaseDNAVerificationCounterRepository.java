package com.ml.mutantdetector.repository;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.ml.mutantdetector.model.DNAVerificationCounter;

@Repository
public class FirebaseDNAVerificationCounterRepository implements DNAVerificationCounterRepository {

	private static Firestore db;
	
	static {
		db = FirestoreClient.getFirestore();		
	}
	
	@Override
	public void incrementCounter(String counterType) {		
		DocumentReference docReference = db.collection("dnaVerificationCounter").document(counterType);
		docReference.update("count", FieldValue.increment(1));
	}

	@Override
	public DNAVerificationCounter getCounter(String counterType) throws InterruptedException, ExecutionException {
		ApiFuture<DocumentSnapshot> future = db.collection("dnaVerificationCounter").document(counterType).get();
		
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
