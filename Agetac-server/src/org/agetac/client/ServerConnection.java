package org.agetac.client;

import org.agetac.client.exception.BadResponseException;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class ServerConnection {

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


	public void putResource(String resType, String resUniqueID,	Representation resRepresentation) throws BadResponseException {

		String url = baseUrl() + resType;
		
		if(resUniqueID != null){
			url += "/" + resUniqueID;
		}
		System.out.println("PUT : " + url);
		ClientResource client = new ClientResource(url);
		
		try {
			client.put(resRepresentation);
		} catch (ResourceException e) {
			throw(new BadResponseException(client.getResponse()));
		}
		
	}

	public void postResource(String resType, String resUniqueID, Representation resRepresentation) throws BadResponseException {

		String url = baseUrl() + resType;
		
		if(resUniqueID != null){
			url += "/" + resUniqueID;
		}
		System.out.println("POST : " + url);
		ClientResource client = new ClientResource(url);
		
		try {
			client.post(resRepresentation);
		} catch (ResourceException e) {
			throw(new BadResponseException(client.getResponse()));
		}
		
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
