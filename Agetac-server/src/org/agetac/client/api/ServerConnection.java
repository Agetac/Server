package org.agetac.client.api;

import org.agetac.common.api.ServerApi;
import org.agetac.common.exception.BadResponseException;
import org.restlet.data.ChallengeRequest;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class ServerConnection implements ServerApi{

	private String host;
	private String contextRoot;
	private String port;

	public ServerConnection(String host, String port, String contextRoot) {

		this.host = host;
		this.port = port;
		this.contextRoot = contextRoot;

	}
	

	public Representation getResource(String resType, String resUniqueID) throws BadResponseException{
		
		String url = baseUrl() + resType;
		
		if(resUniqueID != null){
			url +=  "/" + resUniqueID;
		}
		
		System.out.println("GET : " + url);
		
		ClientResource client = new ClientResource(url);
		
		
		Representation repr = null;
		
		try {
			repr = client.get();
		} catch (Exception e) {
			throw(new BadResponseException(client.getResponse()));
		} 
		
		return repr;
	}
	
/*
	public Representation getResource(String resType, String resUniqueID) throws BadResponseException{
		
		String url = baseUrl() + resType;
		
		if(resUniqueID != null){
			url +=  "/" + resUniqueID;
		}
		
		System.out.println("GET : " + url);
		
		Representation repr = null;
		
		ClientResource client = new ClientResource(url);
		
		client.setChallengeResponse(ChallengeScheme.HTTP_DIGEST, "login", "secret");
		// Send the first request with unsufficient authentication.
		try {
		    repr = client.get();
		}
		catch (ResourceException e) {
		}
		// Should be 401, since the client needs some data sent by the server in
		// order to complete the ChallengeResponse.
		System.out.println(client.getStatus());

        // Complete the challengeResponse object according to the server's data
        // 1- Loop over the challengeRequest objects sent by the server.
        ChallengeRequest c1 = null;
        for (ChallengeRequest challengeRequest : client.getChallengeRequests()) {
            if (ChallengeScheme.HTTP_DIGEST.equals(challengeRequest.getScheme())) {
                c1 = challengeRequest;
                break;
            }
        }

        // 2- Create the Challenge response used by the client to authenticate
        // its requests.
        client.setChallengeResponse(new ChallengeResponse(c1, client.getResponse(), "login", "secret".toCharArray()));
        // Try authenticated request
        
		try {
		    repr = client.get();
		}
		catch (ResourceException e) {
			throw(new BadResponseException(client.getResponse()));
		}
        // Should be 200.
        System.out.println(client.getStatus());
		
		return repr;
	}
*/
	public Representation putResource(String resType, String resUniqueID,	Representation resRepresentation) throws BadResponseException {

		
		String url = baseUrl() + resType;
		
		if(resUniqueID != null){
			url += "/" + resUniqueID;
		}
		
		System.out.println("PUT : " + url);
		
		ClientResource client = new ClientResource(url);
		
		Representation repr = null;
		try {
			repr = client.put(resRepresentation);
		} catch (ResourceException e) {
			throw(new BadResponseException(client.getResponse()));
		}
		return repr;
	}
	/*
	public void putResource(String resType, String resUniqueID,	Representation resRepresentation) throws BadResponseException {

		String url = baseUrl() + resType;
		
		if(resUniqueID != null){
			url += "/" + resUniqueID;
		}
		System.out.println("PUT : " + url);
		ClientResource client = new ClientResource(url);
		
		client.setChallengeResponse(ChallengeScheme.HTTP_DIGEST, "login", "secret");
		// Send the first request with unsufficient authentication.
		try {
			client.put(resRepresentation);
		} catch (ResourceException e) {
		}
		// Should be 401, since the client needs some data sent by the server in
		// order to complete the ChallengeResponse.
		System.out.println(client.getStatus());

        // Complete the challengeResponse object according to the server's data
        // 1- Loop over the challengeRequest objects sent by the server.
        ChallengeRequest c1 = null;
        for (ChallengeRequest challengeRequest : client.getChallengeRequests()) {
            if (ChallengeScheme.HTTP_DIGEST.equals(challengeRequest.getScheme())) {
                c1 = challengeRequest;
                break;
            }
        }

        // 2- Create the Challenge response used by the client to authenticate
        // its requests.
        client.setChallengeResponse(new ChallengeResponse(c1, client.getResponse(), "login", "secret".toCharArray()));
        // Try authenticated request
		try {
			client.put(resRepresentation);
		} catch (ResourceException e) {
			throw(new BadResponseException(client.getResponse()));
		}
        // Should be 200.
        System.out.println(client.getStatus());
		
	}
*/
	public Representation postResource(String resType, String resUniqueID, Representation resRepresentation) throws BadResponseException {

		String url = baseUrl() + resType;
		
		if(resUniqueID != null){
			url += "/" + resUniqueID;
		}
		System.out.println("POST : " + url);
		ClientResource client = new ClientResource(url);
		
		Representation repr = null;
		try {
			repr = client.post(resRepresentation);
		} catch (ResourceException e) {
			throw(new BadResponseException(client.getResponse()));
		}
		
		return repr;
		
	}

	
	public void deleteResource(String resType, String resUniqueID) throws BadResponseException {

		String url = baseUrl() + resType;
		
		if(resUniqueID != null){
			url += "/" + resUniqueID;
		}
		System.out.println("DEL : " + url);
		ClientResource client = new ClientResource(url);
		
		try {
			client.delete();
		} catch (ResourceException e) {
			throw(new BadResponseException(client.getResponse()));
		}
		
	}

	private String baseUrl() {
		return "http://" + host + ":" + port + "/" + contextRoot + "/";
	}

}
