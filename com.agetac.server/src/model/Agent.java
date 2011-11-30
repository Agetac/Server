package model;

import java.util.List;

import org.json.JSONObject;

public class Agent {

	private int id;
	private String nom;
	private Aptitude aptitude;

	private Agent superieur = null;
	private List<Agent> subordonnes = null;
	
	public Agent() {
		super();
		this.nom = "";
		this.aptitude = null;
		this.superieur = null;
		this.subordonnes = null;
	}
	
	public Agent(String nom, Aptitude aptitude, Agent superieur,
			List<Agent> subordonnes) {
		super();
		this.nom = nom;
		this.aptitude = aptitude;
		this.superieur = superieur;
		this.subordonnes = subordonnes;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Agent getSuperieur() {
		return superieur;
	}

	public void setSuperieur(Agent superieur) {
		this.superieur = superieur;
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
		sb.append("id:");
		sb.append(this.id);
		sb.append(",nom:");
		sb.append(this.nom);
		sb.append(",aptitude:");
		sb.append(this.aptitude);
		sb.append(",superieur:");
		sb.append(this.superieur);
		sb.append(",subordonnes:");
		sb.append(this.subordonnes);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Convert this object to a JSON object for representation
	 */
	public JSONObject toJSON() {
		try {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("id", this.id);
			jsonobj.put("nom", this.nom);
			jsonobj.put("aptitude", this.aptitude);
			jsonobj.put("superieur", this.superieur);
			jsonobj.put("subordonnes", this.subordonnes);
			return jsonobj;
		} catch (Exception e) {
			return null;
		}
	}
}
