package model;

import java.util.ArrayList;

public class Caserne {
	
	private String nom;
	private ArrayList<Moyen> moyens;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Moyen> getMoyens() {
		return moyens;
	}

	public void setMoyens(ArrayList<Moyen> moyens) {
		this.moyens = moyens;
	}

	public Caserne(String nom, ArrayList<Moyen> moyens) {
		super();
		this.nom = nom;
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
