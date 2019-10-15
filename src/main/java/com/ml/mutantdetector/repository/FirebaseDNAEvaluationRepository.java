package com.ml.mutantdetector.repository;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.ml.mutantdetector.configuration.AppConfiguration;
import com.ml.mutantdetector.model.DNAEvaluation;

/**
 * Implementation {@link DNAEvaluationRepository} with Firebase database.
 * 
 * @author eschnider
 */
@Repository
public class FirebaseDNAEvaluationRepository implements DNAEvaluationRepository {

	//private Firestore db = FirestoreClient.getFirestore();
	
	static {
		//db = FirestoreClient.getFirestore();		
	}
	
	@Override
	public void save(DNAEvaluation dnaEvaluation) {
		String key = DNAEvaluation.getKey(dnaEvaluation.getDna());
		DocumentReference docRef = AppConfiguration.getAppConfiguration().getDB()
				.collection("dnaVerification").document(key);
		docRef.set(dnaEvaluation);		
	}

	@Override
	public DNAEvaluation get(String key) throws InterruptedException, ExecutionException  {
		ApiFuture<DocumentSnapshot> future = AppConfiguration.getAppConfiguration().getDB()
				.collection("dnaVerification").document(key).get();
		
		DocumentSnapshot document;
		try {
			document = future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw e;
		}
		
		if (document.exists()) {
		  return document.toObject(DNAEvaluation.class);
		}

		return null;
	}

}
