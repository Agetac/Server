package model;

import java.util.ArrayList;

public class Caserne {
	ArrayList<Moyen> moyens;
	String nom;
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

	@Override
	public String toString() {
		return "Caserne [nom=" + nom + ", moyens=" + moyens + "]";
	}
	
	
}
