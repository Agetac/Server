package org.agetac.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.agetac.model.sign.IJsonable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Intervention implements IJsonable{

	private Position lieu;

	private List<Vehicule> vehicules;
	private List<Cible> cibles;
	private List<Source> sources;
	private List<Action> actions;
	private List<Message> messages;
	private List<Implique> impliques;
	
	private String uniqueID;
	
	public Intervention(String uniqueID) {
		super();
		
		this.uniqueID = uniqueID;
		this.lieu = new Position(0,0);
		this.vehicules = new ArrayList<Vehicule>();
		this.cibles = new ArrayList<Cible>();
		this.sources = new ArrayList<Source>();
		this.actions = new ArrayList<Action>();
		this.messages = new ArrayList<Message>();
		this.impliques = new ArrayList<Implique>();
	}

	
	public Intervention(JSONObject json) {
		
		this.vehicules = new ArrayList<Vehicule>();
		this.cibles = new ArrayList<Cible>();
		this.sources = new ArrayList<Source>();
		this.actions = new ArrayList<Action>();
		this.messages = new ArrayList<Message>();
		this.impliques = new ArrayList<Implique>();
		
		try {
			this.uniqueID = (String) json.get("uniqueID");
			
			this.lieu = new Position(json.getJSONObject("lieu"));
			
			JSONArray jsar = json.getJSONArray("vehicules");
			for (int i=0; i< jsar.length(); i++){
				vehicules.add(new Vehicule(jsar.getJSONObject(i)));
			}
			
			jsar = json.getJSONArray("cibles");
			for (int i=0; i< jsar.length(); i++){
				cibles.add(new Cible(jsar.getJSONObject(i)));
			}
			
			jsar = json.getJSONArray("sources");
			for (int i=0; i< jsar.length(); i++){
				sources.add(new Source(jsar.getJSONObject(i)));
			}
			
			jsar = json.getJSONArray("actions");
			for (int i=0; i< jsar.length(); i++){
				actions.add(new Action(jsar.getJSONObject(i)));
			}
			
			jsar = json.getJSONArray("messages");
			for (int i=0; i< jsar.length(); i++){
				messages.add(new Message(jsar.getJSONObject(i)));
			}
			
			jsar = json.getJSONArray("impliques");
			for (int i=0; i< jsar.length(); i++){
				impliques.add(new Implique(jsar.getJSONObject(i)));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void demandeVehicule() {

	}

	public String toString() {
		return "Intervention [vehicules=" + vehicules + ", lieu=" + lieu + "]";
	}
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		try {
			
			json.put("uniqueID", this.uniqueID);
			json.put("lieu", this.lieu.toJson());
			json.put("vehicules", this.vehicules);
			json.put("cibles", this.cibles);
			json.put("sources", this.sources);
			json.put("actions", this.actions);
			json.put("messages", this.messages);
			json.put("impliques", this.impliques);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return json;
	}
	
	@Override
	public IJsonable fromJson(JSONObject json) {
		return new Intervention(json);
	}
	
	/*
	 * GETTER & SETTER
	 */
	
	public Position getLieu() {
		return lieu;
	}

	public void setLieu(Position lieu) {
		this.lieu = lieu;
	}

	public List<Vehicule> getVehicules() {
		return vehicules;
	}

	public void setVehicules(List<Vehicule> vehicules) {
		this.vehicules = vehicules;
	}

	public List<Cible> getCibles() {
		return cibles;
	}

	public void setCibles(List<Cible> cibles) {
		this.cibles = cibles;
	}

	public List<Source> getSources() {
		return sources;
	}

	public void setSources(List<Source> sources) {
		this.sources = sources;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<Implique> getImpliques() {
		return impliques;
	}

	public void setImpliques(List<Implique> impliques) {
		this.impliques = impliques;
	}


	public String getUniqueID() {
		return this.uniqueID;
	}





}
