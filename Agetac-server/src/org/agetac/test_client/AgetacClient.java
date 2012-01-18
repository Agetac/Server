package org.agetac.test_client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

import org.agetac.common.Agent;
import org.agetac.common.Aptitude;
import org.agetac.common.Intervention;
import org.agetac.common.Message;
import org.agetac.common.Position;
import org.agetac.util.Lecture;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

/**
 * A simple example of a "client" class for the AgetacServer.
 * 
 */
public class AgetacClient {

	/**
	 * Connect to a running AgetacServer and perform the requested operation.
	 * 
	 * @param args
	 * @throws Exception
	 *             If problems occur.
	 */
	public static void main(String[] args) throws Exception {

		String host = "http://localhost:8111";
		String contextRoot = "/agetacserver/";

		String resource;
		String uniqueID;
		String operation;

		Lecture lec = new Lecture();

		resource = lec.askInput("Ressource : ");
		uniqueID = lec.askInput("UniqueID : ");

		String url = host + contextRoot + resource + "/" + uniqueID;
		ClientResource client = new ClientResource(url);

		operation = lec.askInput("Opération : ");

		try {
			if ("get".equals(operation)) {
				
				System.out.println("GET");
				if ("agent".equals(resource)) {
					JsonRepresentation representation = new JsonRepresentation(client.get());
					Agent agent = new Agent(representation.getJsonObject());
					System.out.println(agent.toString());
				}
				
				if("message".equals(resource)){
					JsonRepresentation representation = new JsonRepresentation(	client.get());
					Message msg = new Message(representation.getJsonObject());
					System.out.println(msg.toString());
				}
				
			} else if ("delete".equals(operation)) {
				
				System.out.println("DELETE");
				client.delete();
				
			} else if ("put".equals(operation)) {
				
				System.out.println("PUT");
				
				if (resource.equals("agent")) {
					Agent agent = new Agent(uniqueID, "bob",new Aptitude("CDC"), null);
					JsonRepresentation representation = new JsonRepresentation(agent.toJson());
					client.put(representation);
				}
				
				if (resource.equals("message")) {
					Message msg = new Message(uniqueID, "Mon super message.", "0105");
					JsonRepresentation representation = new JsonRepresentation(msg.toJson());
					client.put(representation);
				}
				
			}
		} catch (ResourceException e) {
			// If the operation didn't succeed, indicate why here.
			System.out.println("Error: " + e.getStatus());
		}
	}
}
