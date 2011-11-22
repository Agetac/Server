package model;

import java.util.ArrayList;

public class Intervention {
	ArrayList<Moyen> moyens = new ArrayList<Moyen>();
	String lieu;
	SDIS sdis;
	
	
	public Intervention(String lieu, SDIS sdis) {
		super();
		this.lieu = lieu;
		this.sdis = sdis;
	}
	
	public void demandeMoyen() {
		
	}
	



	public String toString() {
		return "Intervention [moyens=" + moyens + ", lieu=" + lieu + "]";
	}
	
}
