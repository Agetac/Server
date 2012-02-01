package org.agetac.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.agetac.common.Message;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;

public class InterventionConnection {

	private String interId;
	private ServerConnection serv;

	public InterventionConnection(String interId, ServerConnection serv) {

		this.interId = interId;
		this.serv = serv;

	}

	public Message getMessage(String msgId) {
		Message m = null;
		JsonRepresentation representation = null;
		
		if(msgId == null){
			return null;
		}
		
		Representation repr = serv.getResource("intervention/" + interId + "/message", msgId);

		try {
			representation = new JsonRepresentation(repr);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			m = new Message(representation.getJsonObject());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return m;
	}

	public List<Message> getMessages(){
		
		List<Message> messages = new ArrayList<Message>();
		JsonRepresentation representation = null;

		// Récupération de la liste des messages
		Representation repr = serv.getResource("intervention/"+interId+"/message", null);
		
		try	{
			representation = new JsonRepresentation(repr);
			
			JSONArray ar = representation.getJsonArray(); // Récupération de la liste des messages
			
			for (int i=0; i<ar.length(); i++){
				messages.add(new Message(ar.getJSONObject(i)));
			}
			
		}catch(Exception e){
			System.out.println("Error: " + e.toString());
		}
		
		
		return messages;
	}

	public void putMessage(Message msg) {
		Representation r = new JsonRepresentation(msg.toJson());
		serv.putResource("intervention/" + interId + "/message", msg.getUniqueID(), r);
	}
	
	
	public void deleteMessage(String msgId){
		serv.deleteResource("intervention/"+ interId +"/message", msgId);
	}
	
	
	
	
	
}
