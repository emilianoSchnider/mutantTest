/**
 * 
 */
package com.ml.mutantdetector.repository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

/**
 * @author eschnider
 *
 */
public class DNAValidationRepositoryImpl {
	private static final String AUTH_DATABASE = "{\r\n" + 
			"  \"type\": \"service_account\",\r\n" + 
			"  \"project_id\": \"mutant-ca9d1\",\r\n" + 
			"  \"private_key_id\": \"666c1050a0164ae5dd9b2b56bcab94ac09dea67a\",\r\n" + 
			"  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC7KTMu6BxIaMNw\\nssYURZN8aiyyVouWBmDHDakUxwSH9oLiLBFKoy2E4Q4UlUj0PSCVlU3J5rdJU5pb\\nKBplL8hZf1qjY8bcy58HeBfHtDSPqp13V3Q0kyRtTdquGGfHXH4m1YQfAUg53yhX\\nEFEPGrMRviCpy1gKmq2ErLLwmRnd0x2zK/7zuBBnzWcu8/GZWhZrGoPSBh/cUSTy\\nt6SsXfoL9C4gvmVkLCDk7c9Nf49cMPdMyrRKmNfsCkXkVJ8FF59wd+vFiWHqmmut\\nGIOy5V07hKvWG4LX1cW7YxPgzYQBbOEiHHUGoGNyuC1WSR8u/fcnTx4oHe3lucvL\\nEbHxztg1AgMBAAECggEAAy4vt3lW7wgQt3viOnqgMfoKk7voF/7jFXQu+Qe/akwP\\nF1F7uGf4ywxD//zgG35lF7BM0m8q6C0VL2gJYsoivm/tQLTeWTwrPKZBc9xPvwKS\\n4HH1AJELimv17lrDJgkf/6rwhCeybCwASdJ2KNU2+8iI/ORUCldsjBefMXrN4g4V\\nP58Kft7M5vqA7yGBL8TMcW+3gLBEc0bhzDntkk8/E1xHceOoEBDhtVJzmd5GSCJg\\nUVSGBMMJfDFYrtp39LS5NauM40sRPUHWdWk/nS6rIle619rSVCoq339j70QRYajW\\n4pFwd9N7mvCI+Ca4EbHUDXaYWtft4J9knx+e0nRNdQKBgQDcMlRvQbEz+WlGMRmz\\ng6BvCt62wl/yb5ey8bpmdbqRgcEHlsh0lodMvNhZpDHU47Wod0M+4fCeQsn0XSZO\\n0ALvFbcO3mITOoq6xsa6B8aMtCKwL1JjBEUodlkyr2z82kzXJxR/2fC6DvCLCSBh\\nSNeBkywOAplh3gd0buHTBVQ8AwKBgQDZl8M6qdYuPVXR//lHXsBFeS0ab0B3PdS0\\ny34EdgxLXCTd3GJzbxpYSSYHLEhA7ZCVAygiluzSx3rPmtafeQsNvrnWp99jdG4o\\nJUt/tEASuqzg2h++XThpZVAykdiGcwIvDKgX0viVwOwNHESop+DxaYnbe1kDIToP\\n+zPKhk+RZwKBgQC2pKiUgBDT45UGu8Ntu0hf0sxZG3EqTpWtwAO06QIPn2jVOqSa\\n0CD57com0KLkytvrGMGWbyJ+ozeQkZMs3C0S0xg8JQmEmZJoymgb9kcifmgna5kS\\nuIAEx+XEvO3R7eC+tZOzWLrsGWg8ax2IlDrtJ33rQQyouNnGIhL+TfVIsQKBgQCi\\niArrMdtrVKNbJmHUXsEt47K+ESXNK+FLXsj1mhva2qwrEKh1YyMBmToPl5Ms9IbP\\n17jOBYBGfXyNwDHakvafwLxdy61AQ8LYt+rqLADEPK5Pkn6tmkI979B0xZ4/535V\\nGJYinZLTgXSQ4nGirSuIB/ZSfJNjlBkjwNbRy3t5twKBgQCDh2nGmjVKAzDXYVUO\\nXyH/apIw8CArk1+2b8RjO568htDrKz/ojoH8/IW6gbnuuO9wYv2nI1QormYSJ1WG\\nN/vz/6krZCmZgeslD6nVtq0jkbQJUi6eZT/cwsNeD++7cFEf1X7gHFDQkWFH//e9\\nISn14m2Fqb0ImyfwsIzHEA71Jg==\\n-----END PRIVATE KEY-----\\n\",\r\n" + 
			"  \"client_email\": \"firebase-adminsdk-3qcii@mutant-ca9d1.iam.gserviceaccount.com\",\r\n" + 
			"  \"client_id\": \"105800541155412067618\",\r\n" + 
			"  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\r\n" + 
			"  \"token_uri\": \"https://oauth2.googleapis.com/token\",\r\n" + 
			"  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\r\n" + 
			"  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-3qcii%40mutant-ca9d1.iam.gserviceaccount.com\"\r\n" + 
			"}";
	
	private static InputStream serviceAccount;
	private static FirebaseOptions options;
	private static Firestore db;
	
	static {
		serviceAccount = new ByteArrayInputStream(AUTH_DATABASE.getBytes());
		try {
			options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//FirebaseApp.initializeApp(options);
		db = FirestoreClient.getFirestore();		
	}
	
	public void guardar() {
		System.out.println("-- Guardar 0 --.");
		//DocumentReference docRef = db.collection("secuenciasADN").document(UUID.randomUUID().toString());
		DocumentReference docRef = db.collection("secuenciasADN").document("EMI1");
		
		
		System.out.println("-- Guardar 1 --.");
		Map<String, Object> data = new HashMap<>();
		//data.put("dna", LocalDateTime.now().toString());
		data.put("lastUpdate", Arrays.asList("ss","ss"));
		data.put("mutante", true);
		System.out.println("-- Guardar 2 --.");
		ApiFuture<WriteResult> result = docRef.set(data);		
		System.out.println("-- Guardar 3 --.");
	}
}
