package org.agetac.model.impl;

import org.agetac.model.impl.EtatVehicule;
import org.agetac.model.sign.AbstractModel;
import org.agetac.model.sign.IJsonable;
import org.json.JSONException;
import org.json.JSONObject;

public class Vehicule extends AbstractModel {

	private Caserne caserne;
	private EtatVehicule etat;
	private Groupe groupe;

	public Vehicule(String uid, String nom, Position position, Caserne caserne, EtatVehicule etat, Groupe groupe) {
		super(uid, nom, position);
		this.caserne = caserne;
		this.etat = etat;
		this.groupe = groupe;
	}
	
	public Vehicule(JSONObject json) {
		super(json);
		//System.out.println("Entree : " + json.toString());
		try {
			this.caserne = new Caserne(json.getJSONObject("caserne"));
			this.etat = EtatVehicule.valueOf(json.getString("etat"));
			this.groupe = new Groupe(json.getJSONObject("groupe"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public Caserne getCaserne() {
		return caserne;
	}
	
	public void setCaserne(Caserne caserne) {
		this.caserne = caserne;
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
	public JSONObject toJson() {
		JSONObject json = null;
		try {
			json = super.toJson();
			json.put("caserne", this.caserne.toJson());
			json.put("etat", this.etat.name());
			json.put("groupe", this.groupe.toJson());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("Sortie : " + json.toString());
		return json;
	}

	@Override
	public IJsonable fromJson(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}
}
