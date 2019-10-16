package com.ml.mutantdetector.configuration;

import java.io.FileInputStream;
import java.net.URI;
import java.util.List;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class AppConfiguration {	

	private static FirebaseOptions options;
	
	private static AppConfiguration appConfiguration;
	
	public static AppConfiguration getAppConfiguration() {
		if (appConfiguration == null) {
			appConfiguration = new AppConfiguration();
		}
		
		return appConfiguration;
	}
	
	private AppConfiguration() { 			
		
		FileInputStream serviceAccount;
		try {
			URI uri = getClass().getClassLoader().getResource("firebase.json").toURI();
			serviceAccount = new FileInputStream(uri.getPath());
			options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	


		boolean hasBeenInitialized=false;
		List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
		for(FirebaseApp app : firebaseApps){
			System.out.print(app.getName());
		    if(app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)){
		        hasBeenInitialized=true;		        
		    }
		}
		
		if (!hasBeenInitialized) {
			FirebaseApp.initializeApp(options);
		}		
	}
	
	public Firestore getDB() {
		return FirestoreClient.getFirestore();
	}
}
