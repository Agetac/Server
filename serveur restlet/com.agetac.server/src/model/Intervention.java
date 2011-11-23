package model;

import java.util.ArrayList;

public class Intervention {
	ArrayList<Moyen> moyens = new ArrayList<Moyen>();
	String lieu;
	Caserne caserne;
	
	public Intervention(String lieu, Caserne cas) {
		super();
		this.lieu = lieu;
		this.caserne = cas;
	}
	
	public void demandeMoyen() {
		moyens.add(caserne.requestMoyen());
	}



	public String toString() {
		return "Intervention [moyens=" + moyens + ", lieu=" + lieu + "]";
	}
	
}
