package org.agetac.common;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Caserne {
	
	private String nom;
	private List<Moyen> moyens;
	
	public Caserne(String nom, List<Moyen> moyens) {
		super();
		this.nom = nom;
		this.moyens = moyens;
	}
	
	public Caserne(JSONObject json){
		try {
			this.nom = json.getString("nom");
			
			JSONArray jsar = json.getJSONArray("moyens");
			for (int i=0; i< jsar.length(); i++){
				moyens.add(new Moyen(jsar.getJSONObject(i)));
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Moyen> getMoyens() {
		return moyens;
	}

	public void setMoyens(ArrayList<Moyen> moyens) {
		this.moyens = moyens;
	}
	
	public Moyen requestMoyen() {
		if (! moyens.isEmpty() ) {
			Moyen v = moyens.get(0);
			moyens.remove(0);
			return v;
		}
		else { return null;}
	}

	public String toString() {
		 StringBuffer sb = new StringBuffer();
		 sb.append("nom:");
		 sb.append(this.nom);
		 sb.append(", moyens:");
		 sb.append(this.moyens);
		 return sb.toString();
		}
	
	
}
