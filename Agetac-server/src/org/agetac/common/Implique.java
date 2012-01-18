package org.agetac.common;

import org.json.JSONException;
import org.json.JSONObject;

public class Implique {
	private EtatImplique etat;

	public Implique(EtatImplique etat) {
		this.etat = etat;
	}
	
	public Implique (JSONObject json){
		try {
			this.etat = new EtatImplique(json.getString("etat"));
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
	public JSONObject toJSON() {
		try {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("etat", etat.toJSON());
			return jsonobj;
		} catch (Exception e) {
			return null;
		}
	}
}
