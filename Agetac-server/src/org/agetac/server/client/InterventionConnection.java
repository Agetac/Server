package org.agetac.server.client;

import java.io.IOException;

import org.agetac.common.Message;
import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;

public class InterventionConnection{

	private String interId;
	private ServerConnection serv;
	
	public InterventionConnection(String interId, ServerConnection serv){
		
		this.interId = interId;
		this.serv = serv;
		
	}
	
	public Message getMessage(String msgId){
		Message m = null;
		JsonRepresentation representation = null;
		
		Representation repr = serv.getResource("intervention/"+interId+"/message", msgId);
		
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
	
	public void putMessage(Message msg){
		
		Representation r = new JsonRepresentation(msg.toJson());
		
		serv.putResource("intervention/"+interId+"/message", msg.getUniqueID(), r);
		
	}
}
