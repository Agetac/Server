package org.agetac.common;

import org.json.JSONException;
import org.json.JSONObject;

public class EtatVehicule {
	private String etat;

	public EtatVehicule(String etat) {
		this.etat = etat;
	}
	
	public EtatVehicule(JSONObject json){
		try {
			this.etat = json.getString("etat");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	/**
	 * Convert this object to a JSON object for representation
	 */
	public JSONObject toJSON() {
		try {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("etat", etat);
			return jsonobj;
		} catch (Exception e) {
			return null;
		}
	}
}
