package org.agetac.common;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class Agent implements IJsonable{

	private String nom;
	private Aptitude aptitude;

	private List<Agent> subordonnes = null;
	private String uniqueID;
	
	public Agent() {
		super();
		this.nom = "";
		this.aptitude = null;
		this.subordonnes = null;
	}
	
	public Agent(String uniqueId, String nom, Aptitude aptitude, 
			List<Agent> subordonnes) {
		super();
		this.nom = nom;
		this.aptitude = aptitude;
		this.subordonnes = subordonnes;
		this.uniqueID = uniqueId;
	}
	
	public Agent(JSONObject json) {
		try {
			
			this.uniqueID = json.getString("uniqueID");
			this.nom = json.getString("nom");
			this.aptitude = Aptitude.valueOf(json.getString("aptitude"));
			//this.subordonnes = (List<Agent>) json.get("subordonnes");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public String getUniqueId() {
		return this.uniqueID;
	}

	public void setId(String uniqueID) {
		this.uniqueID = uniqueID;
	}
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Aptitude getAptitude() {
		return aptitude;
	}

	public void setAptitude(Aptitude aptitude) {
		this.aptitude = aptitude;
	}

	public List<Agent> getSubordonnes() {
		return subordonnes;
	}

	public void setSubordonnes(List<Agent> subordonnes) {
		this.subordonnes = subordonnes;
	}

	/**
	 * Convert this object to a string for representation
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[Agent ");
		sb.append("uniqueID:");
		sb.append(this.uniqueID);
		sb.append(",nom:");
		sb.append(this.nom);
		sb.append(",aptitude:");
		sb.append(this.aptitude);
		sb.append(",subordonnes:");
		sb.append(this.subordonnes);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Convert this object to a JSON object for representation
	 */
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		try {
			json.put("uniqueID", this.uniqueID);
			json.put("nom", this.nom);
			json.put("aptitude", aptitude.name());
			json.put("subordonnes", this.subordonnes);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public IJsonable fromJson(JSONObject json) {
		return new Agent(json);
	}

}
