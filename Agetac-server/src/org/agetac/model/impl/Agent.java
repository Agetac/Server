package org.agetac.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.agetac.model.impl.Aptitude;
import org.agetac.model.sign.AbstractModel;
import org.agetac.model.sign.IJsonable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Agent extends AbstractModel {

	private Aptitude aptitude;
	private List<Agent> subordonnes;

	public Agent() {
		super("", "", new Position(0,0));
		this.aptitude = null;
		this.subordonnes = null;
	}

	public Agent(String uid, String name, Aptitude aptitude, List<Agent> subordonnes) {
		super(uid, name, new Position(0,0));
		this.aptitude = aptitude;
		this.subordonnes = subordonnes;
	}

	public Agent(JSONObject json) {
		super(json);
		
		try {
			this.aptitude = Aptitude.valueOf(json.getString("aptitude"));
			JSONArray array = json.getJSONArray("subordonnes");
			this.subordonnes = new ArrayList<Agent>();
			for (int i=0; i<array.length(); i++) {
				this.subordonnes.add(new Agent(array.getJSONObject(i)));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
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
		JSONObject json = super.toJson();
		
		try {
			json.put("aptitude", aptitude.name());
			json.put("subordonnes", this.subordonnes);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public IJsonable fromJson(JSONObject json) {
		return new Agent(json);
	}

}
