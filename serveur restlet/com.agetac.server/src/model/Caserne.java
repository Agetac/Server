package model;

import java.util.ArrayList;

public class Caserne {
	ArrayList<Moyen> moyens;
	public Caserne(ArrayList<Moyen> moyens) {
		super();
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
		return "Caserne [moyens=" + moyens + "]";
	}
	
	
}
