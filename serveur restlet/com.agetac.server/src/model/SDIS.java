package model;

import java.util.ArrayList;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class SDIS extends ServerResource{
	Caserne caserne;
	Intervention intervention;
	public SDIS(Caserne caserne) {
		super();
		this.caserne = caserne;
	}
	
	public SDIS() {
		super();
		this.caserne = initCaserne();
	}
	
	public void setIntervention(String nom) {
		intervention = new Intervention(nom, caserne);
	}
	
	@Get
	public String toString() {
		return "SDIS [caserne=" + caserne + ", intervention=" + intervention
				+ "]";
	}
	
	static private Caserne initCaserne() {
		ArrayList<Moyen >moyens = new ArrayList<Moyen>();
		moyens.add(new Moyen("FPT1"));
		moyens.add(new Moyen("FPT2"));
		moyens.add(new Moyen("FPT3"));
		moyens.add(new Moyen("VSAV1"));
		moyens.add(new Moyen("VSAV2"));
		return new Caserne(moyens);
	}
}
