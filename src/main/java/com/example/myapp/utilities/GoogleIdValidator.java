package com.example.myapp.utilities;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

public class GoogleIdValidator {
	
	
 public String validate(String idTokenString)
 {
	 HttpTransport transport = new NetHttpTransport();
	  JsonFactory jsonFactory = new JacksonFactory();
	  
	  
	  System.out.println("THIS IS VALIDATOR --->> "+idTokenString);
	 GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
	     .setAudience(Collections.singletonList("82907325524-jfg57300vs7m0300cs7uk6pvekjckq39.apps.googleusercontent.com"))
	     .build();

	 // (Receive idTokenString by HTTPS POST)
	 System.out.println("THIS IS VALIDATOR --->>1236547126 ");

	 GoogleIdToken idToken;
	try {
		System.out.println("THIS IS VASHkldsdhfkhsdklfgsdjkfgsdjkfbsdfgksdhfgksdhfk");
		idToken = verifier.verify(idTokenString);
		 System.out.println("THIS IS VALIDATOR --->>dsjhdhdsjhdfskjhdfsjdfs");
		 if (idToken != null) {
		   Payload payload = idToken.getPayload();

		   // Print user identifier
		   String userId = payload.getSubject();
		   System.out.println("User ID: " + userId);

		   // Get profile information from payload
		   String email = payload.getEmail();
		   boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
		   String name = (String) payload.get("name");
		   String pictureUrl = (String) payload.get("picture");
		   String locale = (String) payload.get("locale");
		   String familyName = (String) payload.get("family_name");
		   String givenName = (String) payload.get("given_name");

		   return "valid token";

		 } else {
		   return "Invalid ID token";
		 }

		
	} catch (GeneralSecurityException e) {
		// TODO Auto-generated catch block
		System.out.println("inside general security");
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("inside io exception");
		e.printStackTrace();
	}
	 
	return "after catch";
	
	
 }

}
