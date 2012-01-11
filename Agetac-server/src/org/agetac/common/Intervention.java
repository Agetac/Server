package org.agetac.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

public class Intervention {

	private Position lieu;

	private List<Moyen> moyens;
	private List<Cible> cibles;
	private List<Source> sources;
	private List<Action> actions;
	private List<Message> messages;
	private List<Implique> impliques;
	
	private String uniqueID;
	
	public Intervention(String uniqueID) {
		super();
		Random r = new Random();
		
		this.uniqueID = uniqueID;
		this.lieu = new Position(0,0);
		this.moyens = new ArrayList<Moyen>();
		this.cibles = new ArrayList<Cible>();
		this.sources = new ArrayList<Source>();
		this.actions = new ArrayList<Action>();
		this.messages = new ArrayList<Message>();
		this.impliques = new ArrayList<Implique>();
	}

	
	public Intervention(JSONObject json) {
		
		try {
			this.uniqueID = (String) json.get("uniqueID");
			this.lieu = (Position) json.get("lieu");
			this.moyens = (List<Moyen>) json.get("moyens");
			this.cibles = (List<Cible>) json.get("cibles");
			this.sources = (List<Source>) json.get("sources");
			this.actions = (List<Action>) json.get("actions");
			this.messages = (List<Message>) json.get("messages");
			this.impliques = (List<Implique>) json.get("impliques");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void demandeMoyen() {

	}

	public String toString() {
		return "Intervention [moyens=" + moyens + ", lieu=" + lieu + "]";
	}
	public String toJson() {
		JSONObject json = new JSONObject();
		try {
			json.append("uniqueID", this.uniqueID);
			json.append("moyens", this.moyens);
			json.append("cibles", this.cibles);
			json.append("sources", this.sources);
			json.append("actions", this.actions);
			json.append("messages", this.messages);
			json.append("impliques", this.impliques);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return json.toString();
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

	public List<Moyen> getMoyens() {
		return moyens;
	}

	public void setMoyens(List<Moyen> moyens) {
		this.moyens = moyens;
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
