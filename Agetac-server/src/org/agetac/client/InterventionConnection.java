package org.agetac.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.agetac.model.impl.Action;
import org.agetac.model.impl.Cible;
import org.agetac.model.impl.Implique;
import org.agetac.model.impl.Message;
import org.agetac.model.impl.Source;
import org.agetac.model.impl.Vehicule;
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
		
		Representation r = new JsonRepresentation(msg.toJSON());
		
		serv.putResource("intervention/"+interId+"/message", msg.getUniqueID(), r);
		
	}
	

	public void postMessage(Message msg){
		
		Representation r = new JsonRepresentation(msg.toJSON());
		
		serv.postResource("intervention/"+interId+"/message", msg.getUniqueID(), r);
		
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
	
	public List<Vehicule> getVehicules(){

		List<Vehicule> vehicules = new ArrayList<Vehicule>();
		JsonRepresentation representation = null;

		// Récupération de la liste des Vehicules
		Representation repr = serv.getResource("intervention/"+interId+"/vehicule", null);

		try {
			representation = new JsonRepresentation(repr);
	
			JSONArray ar = representation.getJsonArray(); // Récupération de la liste des vehicules
			
			for (int i=0; i<ar.length(); i++){
				vehicules.add(new Vehicule(ar.getJSONObject(i)));
			}

		}catch(Exception e){
			System.out.println("Error: " + e.toString());
		}
		
		return vehicules;
	}
	
	public void putVehicule(Vehicule v){
		
		Representation r = new JsonRepresentation(v.toJSON());
		
		serv.putResource("intervention/"+interId+"/vehicule", v.getUniqueID(), r);
		
	}
	
	public void deleteVehicule(Vehicule v){
		serv.deleteResource("intervention/"+interId+"/vehicule", v.getUniqueID());
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
		
		Representation r = new JsonRepresentation(s.toJSON());
		
		serv.putResource("intervention/"+interId+"/source", s.getUniqueID(), r);
		
	}
	
	public void deleteSource(Source s){
		serv.deleteResource("intervention/"+interId+"/source", s.getUniqueID());
	}
	
	public List<Source> getSources(){

		List<Source> sources = new ArrayList<Source>();
		JsonRepresentation representation = null;

		// Récupération de la liste des messages
		Representation repr = serv.getResource("intervention/"+interId+"/source", null);

		try {
		representation = new JsonRepresentation(repr);

		JSONArray ar = representation.getJsonArray(); // Récupération de la liste des messages

		for (int i=0; i<ar.length(); i++){
			Source src = new Source(ar.getJSONObject(i));
			System.out.println(src.getUniqueID());
			sources.add(src);
		}

		}catch(Exception e){
			System.out.println("Error: " + e.toString());
		}


		return sources;
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
		Representation r = new JsonRepresentation(c.toJSON());
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
		Representation r = new JsonRepresentation(a.toJSON());
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
		Representation r = new JsonRepresentation(i.toJSON());
		serv.putResource("intervention/"+interId+"/implique", i.getUniqueID(), r);
	}
	
	public void deleteImplique(Implique i){
		serv.deleteResource("intervention/"+interId+"/implique", i.getUniqueID());
	}
}
