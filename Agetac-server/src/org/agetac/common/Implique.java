package org.agetac.common;

import org.json.JSONException;
import org.json.JSONObject;

public class Implique implements IJsonable{
	private String uniqueID;
	private EtatImplique etat;

	public Implique(String uniqueId, EtatImplique etat) {
		this.uniqueID = uniqueId;
		this.etat = etat;
	}
	
	public Implique (JSONObject json){
		try {
			this.uniqueID = json.getString("uniqueID");
			this.etat = EtatImplique.valueOf(json.getString("etat"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public EtatImplique getEtat() {
		return etat;
	}

	public void setEtat(EtatImplique etat) {
		this.etat = etat;
	}

	/**
	 * Convert this object to a string for representation
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("etat:");
		sb.append(this.etat);

		return sb.toString();
	}

	/**
	 * Convert this object to a JSON object for representation
	 */
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		try {
			
			json.put("uniqueID", this.uniqueID);
			json.put("etat", etat.name());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public IJsonable fromJson(JSONObject json) {
		return new Implique(json);
	}
	
	public void setUniqueID(String uniqueId) {
		this.uniqueID = uniqueId;
	}
	public String getUniqueID() {
		return this.uniqueID;
	}
}
