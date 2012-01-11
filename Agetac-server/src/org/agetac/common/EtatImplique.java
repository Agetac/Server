package org.agetac.common;

import org.json.JSONObject;

public class EtatImplique {
	private String etat;

	public EtatImplique(String etat) {
		super();
		this.etat = etat;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
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
			jsonobj.put("etat", this.etat);
			return jsonobj;
		} catch (Exception e) {
			return null;
		}
	}
}
