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
		
		Representation repr = null;
		
		ClientResource client = new ClientResource(url);
		
		client.setChallengeResponse(ChallengeScheme.HTTP_DIGEST, "login", "secret");
		// Envoie une premi�re requ�te avec une authentification insuffisante
		try {
		    repr = client.get();
		}
		catch (ResourceException e) {
		}
		// La r�ponse devrait �tre un 401, le client a besoin des donn�es envoy�es par le serveur pour compl�ter le ChallengeResponse
        // 1- It�ration sur les objets ChallengeResponse envoy�s par le serveur pour compl�ter le client
        ChallengeRequest c1 = null;
        for (ChallengeRequest challengeRequest : client.getChallengeRequests()) {
            if (ChallengeScheme.HTTP_DIGEST.equals(challengeRequest.getScheme())) {
                c1 = challengeRequest;
                break;
            }
        }

        // 2- Cr�ation du ChallengeResponse utilis� par le client pour authentifier sa requ�te
        client.setChallengeResponse(new ChallengeResponse(c1, client.getResponse(), "login", "secret".toCharArray()));
        
        //Devrait �tre un 200 si l'authentification s'est bien deroul�
		try {
		    repr = client.get();
		}
		catch (ResourceException e) {
			throw(new BadResponseException(client.getResponse()));
		}
		
		return repr;
	}
	
	public Representation putResource(String resType, String resUniqueID,	Representation resRepresentation) throws BadResponseException {
		
		String url = baseUrl() + resType;
		
		if(resUniqueID != null){
			url +=  "/" + resUniqueID;
		}
		
		System.out.println("PUT : " + url);
		
		Representation repr = null;
		
		ClientResource client = new ClientResource(url);
		
		client.setChallengeResponse(ChallengeScheme.HTTP_DIGEST, "login", "secret");
		// Envoie une premi�re requ�te avec une authentification insuffisante
		try {
		    repr = client.put(null);
		}
		catch (ResourceException e) {
		}
		// La r�ponse devrait �tre un 401, le client a besoin des donn�es envoy�es par le serveur pour compl�ter le ChallengeResponse
        // 1- It�ration sur les objets ChallengeResponse envoy�s par le serveur pour compl�ter le client
        ChallengeRequest c1 = null;
        for (ChallengeRequest challengeRequest : client.getChallengeRequests()) {
            if (ChallengeScheme.HTTP_DIGEST.equals(challengeRequest.getScheme())) {
                c1 = challengeRequest;
                break;
            }
        }

        // 2- Cr�ation du ChallengeResponse utilis� par le client pour authentifier sa requ�te
        client.setChallengeResponse(new ChallengeResponse(c1, client.getResponse(), "login", "secret".toCharArray()));
        
        //Devrait �tre un 200 si l'authentification s'est bien deroul�
		try {
		    repr = client.put(resRepresentation);
		}
		catch (ResourceException e) {
			throw(new BadResponseException(client.getResponse()));
		}
		
		return repr;
	}

	public Representation postResource(String resType, String resUniqueID, Representation resRepresentation) throws BadResponseException {
		
		String url = baseUrl() + resType;
		
		if(resUniqueID != null){
			url +=  "/" + resUniqueID;
		}
		
		System.out.println("POST : " + url);
		
		Representation repr = null;
		
		ClientResource client = new ClientResource(url);
		
		client.setChallengeResponse(ChallengeScheme.HTTP_DIGEST, "login", "secret");
		// Envoie une premi�re requ�te avec une authentification insuffisante
		try {
		    repr = client.post(null);
		}
		catch (ResourceException e) {
		}
		// La r�ponse devrait �tre un 401, le client a besoin des donn�es envoy�es par le serveur pour compl�ter le ChallengeResponse
        // 1- It�ration sur les objets ChallengeResponse envoy�s par le serveur pour compl�ter le client
        ChallengeRequest c1 = null;
        for (ChallengeRequest challengeRequest : client.getChallengeRequests()) {
            if (ChallengeScheme.HTTP_DIGEST.equals(challengeRequest.getScheme())) {
                c1 = challengeRequest;
                break;
            }
        }

        // 2- Cr�ation du ChallengeResponse utilis� par le client pour authentifier sa requ�te
        client.setChallengeResponse(new ChallengeResponse(c1, client.getResponse(), "login", "secret".toCharArray()));
        
        //Devrait �tre un 200 si l'authentification s'est bien deroul�
		try {
		    repr = client.post(resRepresentation);
		}
		catch (ResourceException e) {
			throw(new BadResponseException(client.getResponse()));
		}
		
		return repr;
	}

	
	public void deleteResource(String resType, String resUniqueID) throws BadResponseException {
		
		String url = baseUrl() + resType;
		
		if(resUniqueID != null){
			url +=  "/" + resUniqueID;
		}
		
		System.out.println("DEL : " + url);
		
		ClientResource client = new ClientResource(url);
		
		client.setChallengeResponse(ChallengeScheme.HTTP_DIGEST, "login", "secret");
		// Envoie une premi�re requ�te avec une authentification insuffisante
		try {
		    client.delete();
		}
		catch (ResourceException e) {
		}
		// La r�ponse devrait �tre un 401, le client a besoin des donn�es envoy�es par le serveur pour compl�ter le ChallengeResponse
        // 1- It�ration sur les objets ChallengeResponse envoy�s par le serveur pour compl�ter le client
        ChallengeRequest c1 = null;
        for (ChallengeRequest challengeRequest : client.getChallengeRequests()) {
            if (ChallengeScheme.HTTP_DIGEST.equals(challengeRequest.getScheme())) {
                c1 = challengeRequest;
                break;
            }
        }

        // 2- Cr�ation du ChallengeResponse utilis� par le client pour authentifier sa requ�te
        client.setChallengeResponse(new ChallengeResponse(c1, client.getResponse(), "login", "secret".toCharArray()));
        
        //Devrait �tre un 200 si l'authentification s'est bien deroul�
		try {
		    client.delete();
		}
		catch (ResourceException e) {
			throw(new BadResponseException(client.getResponse()));
		}

	}

	private String baseUrl() {
		return "http://" + host + ":" + port + "/" + contextRoot + "/";
	}

}
