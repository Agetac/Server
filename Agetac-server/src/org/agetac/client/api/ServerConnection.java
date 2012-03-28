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
		// Envoie une première requête avec une authentification insuffisante
		try {
		    repr = client.get();
		}
		catch (ResourceException e) {
		}
		// La réponse devrait être un 401, le client a besoin des données envoyées par le serveur pour compléter le ChallengeResponse
        // 1- Itération sur les objets ChallengeResponse envoyés par le serveur pour compléter le client
        ChallengeRequest c1 = null;
        for (ChallengeRequest challengeRequest : client.getChallengeRequests()) {
            if (ChallengeScheme.HTTP_DIGEST.equals(challengeRequest.getScheme())) {
                c1 = challengeRequest;
                break;
            }
        }

        // 2- Création du ChallengeResponse utilisé par le client pour authentifier sa requête
        client.setChallengeResponse(new ChallengeResponse(c1, client.getResponse(), "login", "secret".toCharArray()));
        
        //Devrait être un 200 si l'authentification s'est bien deroulé
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
		// Envoie une première requête avec une authentification insuffisante
		try {
		    repr = client.put(null);
		}
		catch (ResourceException e) {
		}
		// La réponse devrait être un 401, le client a besoin des données envoyées par le serveur pour compléter le ChallengeResponse
        // 1- Itération sur les objets ChallengeResponse envoyés par le serveur pour compléter le client
        ChallengeRequest c1 = null;
        for (ChallengeRequest challengeRequest : client.getChallengeRequests()) {
            if (ChallengeScheme.HTTP_DIGEST.equals(challengeRequest.getScheme())) {
                c1 = challengeRequest;
                break;
            }
        }

        // 2- Création du ChallengeResponse utilisé par le client pour authentifier sa requête
        client.setChallengeResponse(new ChallengeResponse(c1, client.getResponse(), "login", "secret".toCharArray()));
        
        //Devrait être un 200 si l'authentification s'est bien deroulé
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
		// Envoie une première requête avec une authentification insuffisante
		try {
		    repr = client.post(null);
		}
		catch (ResourceException e) {
		}
		// La réponse devrait être un 401, le client a besoin des données envoyées par le serveur pour compléter le ChallengeResponse
        // 1- Itération sur les objets ChallengeResponse envoyés par le serveur pour compléter le client
        ChallengeRequest c1 = null;
        for (ChallengeRequest challengeRequest : client.getChallengeRequests()) {
            if (ChallengeScheme.HTTP_DIGEST.equals(challengeRequest.getScheme())) {
                c1 = challengeRequest;
                break;
            }
        }

        // 2- Création du ChallengeResponse utilisé par le client pour authentifier sa requête
        client.setChallengeResponse(new ChallengeResponse(c1, client.getResponse(), "login", "secret".toCharArray()));
        
        //Devrait être un 200 si l'authentification s'est bien deroulé
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
		// Envoie une première requête avec une authentification insuffisante
		try {
		    client.delete();
		}
		catch (ResourceException e) {
		}
		// La réponse devrait être un 401, le client a besoin des données envoyées par le serveur pour compléter le ChallengeResponse
        // 1- Itération sur les objets ChallengeResponse envoyés par le serveur pour compléter le client
        ChallengeRequest c1 = null;
        for (ChallengeRequest challengeRequest : client.getChallengeRequests()) {
            if (ChallengeScheme.HTTP_DIGEST.equals(challengeRequest.getScheme())) {
                c1 = challengeRequest;
                break;
            }
        }

        // 2- Création du ChallengeResponse utilisé par le client pour authentifier sa requête
        client.setChallengeResponse(new ChallengeResponse(c1, client.getResponse(), "login", "secret".toCharArray()));
        
        //Devrait être un 200 si l'authentification s'est bien deroulé
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
