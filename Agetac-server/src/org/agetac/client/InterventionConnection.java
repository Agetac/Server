package org.agetac.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.agetac.common.Action;
import org.agetac.common.Cible;
import org.agetac.common.Implique;
import org.agetac.common.Message;
import org.agetac.common.Moyen;
import org.agetac.common.Position;
import org.agetac.common.Source;
import org.agetac.common.Vehicule;
import org.json.JSONArray;
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
	
	public List<Message> getMessages(){

		List<Message> messages = new ArrayList<Message>();
		JsonRepresentation representation = null;

		// Récupération de la liste des messages
		Representation repr = serv.getResource("intervention/"+interId+"/message", null);

		try {
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
	
	public void putMessage(Message msg){
		
		Representation r = new JsonRepresentation(msg.toJson());
		
		serv.putResource("intervention/"+interId+"/message", msg.getUniqueID(), r);
		
	}
	
	public void deleteMessage(Message msg){
		serv.deleteResource("intervention/"+interId+"/message", msg.getUniqueID());
	}
	
	public Vehicule getVehicule(String vId){
		Vehicule v = null;
		JsonRepresentation representation = null;
		
		Representation repr = serv.getResource("intervention/"+interId+"/vehicule", vId);
		
		try {
			representation = new JsonRepresentation(repr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			v = new Vehicule(representation.getJsonObject());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return v;
	}
	
	public void putVehicule(Vehicule v){
		
		Representation r = new JsonRepresentation(v.toJson());
		
		serv.putResource("intervention/"+interId+"/vehicule", v.getUniqueId(), r);
		
	}
	
	public void deleteVehicule(Vehicule v){
		serv.deleteResource("intervention/"+interId+"/vehicule", v.getUniqueId());
	}
	
	public Source getSource(String sId){
		Source s = null;
		JsonRepresentation representation = null;
		
		Representation repr = serv.getResource("intervention/"+interId+"/source", sId);
		
		try {
			representation = new JsonRepresentation(repr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			s = new Source(representation.getJsonObject());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return s;
	}
	
	public void putSource(Source s){
		
		Representation r = new JsonRepresentation(s.toJson());
		
		serv.putResource("intervention/"+interId+"/source", s.getUniqueID(), r);
		
	}
	
	public void deleteSource(Source s){
		serv.deleteResource("intervention/"+interId+"/source", s.getUniqueID());
	}
	
	public Cible getCible(String cId){
		Cible c = null;
		JsonRepresentation representation = null;
		
		Representation repr = serv.getResource("intervention/"+interId+"/cible", cId);
		
		try {
			representation = new JsonRepresentation(repr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			c = new Cible(representation.getJsonObject());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return c;
	}
	
	public void putCible(Cible c){
		Representation r = new JsonRepresentation(c.toJson());
		serv.putResource("intervention/"+interId+"/cible", c.getUniqueID(), r);
	}
	
	public void deleteCible(Cible c){
		serv.deleteResource("intervention/"+interId+"/cible", c.getUniqueID());
	}
	
	public Action getAction(String aId){
		Action a = null;
		JsonRepresentation representation = null;
		
		Representation repr = serv.getResource("intervention/"+interId+"/action", aId);
		
		try {
			representation = new JsonRepresentation(repr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			a = new Action(representation.getJsonObject());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return a;
	}
	
	public void putAction(Action a){
		Representation r = new JsonRepresentation(a.toJson());
		serv.putResource("intervention/"+interId+"/cible", a.getUniqueID(), r);
	}
	
	public void deleteAction(Action a){
		serv.deleteResource("intervention/"+interId+"/cible", a.getUniqueID());
	}
	
	public Implique getImplique(String iId){
		Implique i = null;
		JsonRepresentation representation = null;
		
		Representation repr = serv.getResource("intervention/"+interId+"/implique", iId);
		
		try {
			representation = new JsonRepresentation(repr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			i = new Implique(representation.getJsonObject());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return i;
	}
	
	public void putImplique(Implique i){
		Representation r = new JsonRepresentation(i.toJson());
		serv.putResource("intervention/"+interId+"/implique", i.getUniqueID(), r);
	}
	
	public void deleteImplique(Implique i){
		serv.deleteResource("intervention/"+interId+"/implique", i.getUniqueID());
	}
}
