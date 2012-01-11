package org.agetac.common;

import org.json.JSONObject;

public class Vehicule extends Moyen {

	private EtatVehicule etat;
	private Groupe groupe;

	public Vehicule(String nom, Caserne caserne, Position position,
			EtatVehicule etat, Groupe groupe) {
		super(nom, caserne, position);
		this.etat = etat;
		this.groupe = groupe;
	}

	public EtatVehicule getEtat() {
		return etat;
	}

	public void setEtat(EtatVehicule etat) {
		this.etat = etat;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	/**
	 * Convert this object to a string for representation
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
		sb.append("etat:");
		sb.append(this.etat);
		sb.append(",groupe:");
		sb.append(this.groupe);
		sb.append(",position:");
		return sb.toString();
	}

	/**
	 * Convert this object to a JSON object for representation
	 */
	public JSONObject toJSON() {
		try {
			JSONObject jsonobj = super.toJSON();
			jsonobj.put("etat", this.etat);
			jsonobj.put("groupe", this.groupe);
			return jsonobj;
		} catch (Exception e) {
			return null;
		}
	}
}
