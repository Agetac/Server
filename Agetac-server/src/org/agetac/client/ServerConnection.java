package org.agetac.client;

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

	public Representation getResource(String resType, String resUniqueID) {
		
		String url = baseUrl() + resType;
		
		if(resUniqueID != null){
			url +=  "/" + resUniqueID;
		}
		
		System.out.println("GET : " + url);
		
		ClientResource client = new ClientResource(url);
		
		Representation repr = null;
		
		try {
			repr = client.get();
		} catch (ResourceException e) {
			System.out.println("Error: " + e.getStatus());
		}
		
		return repr;
	}


	public void putResource(String resType, String resUniqueID,	Representation resRepresentation) {

		String url = baseUrl() + resType;
		
		if(resUniqueID != null){
			url += "/" + resUniqueID;
		}
		System.out.println("PUT : " + url);
		ClientResource client = new ClientResource(url);
		
		try {
			client.put(resRepresentation);
		} catch (ResourceException e) {
			System.out.println("Error: " + e.getStatus());
		}
		
	}

	public void deleteResource(String resType, String resUniqueID) {

		String url = baseUrl() + resType;
		
		if(resUniqueID != null){
			url += "/" + resUniqueID;
		}
		System.out.println("DEL : " + url);
		ClientResource client = new ClientResource(url);
		
		try {
			client.delete();
		} catch (ResourceException e) {
			System.out.println("Error: " + e.getStatus());
		}
		
	}

	private String baseUrl() {
		return "http://" + host + ":" + port + "/" + contextRoot + "/";
	}

}
