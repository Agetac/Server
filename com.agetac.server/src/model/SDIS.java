package model;

import java.util.ArrayList;
import java.util.List;


public class SDIS{
	
	List<Caserne> caserne_list;
	ArrayList<Intervention> intervention_list;
	
	
	public SDIS() {
		super();
		this.caserne_list = initCaserne(3);
	}
	
	public SDIS(List<Caserne> casernes) {
		super();
		this.caserne_list = casernes;
	}
	

	
	/*
	 * Création/Ajout d'une intervention
	 */
	public void createIntervention(Position pos) {
		intervention_list.add(new Intervention(pos));
	}
	
	/*
	 * Initialisation des casernes
	 */
	static private List<Caserne> initCaserne(int nbCaserne) {
		List<Caserne> casernes_list = new ArrayList<Caserne>();
		
		for(int i=0; i<nbCaserne; i++){
			ArrayList<Moyen> moyens = new ArrayList<Moyen>();
			moyens.add(new Moyen("FPT1", null, null));
			moyens.add(new Moyen("FPT2", null, null));
			moyens.add(new Moyen("FPT3", null, null));
			moyens.add(new Moyen("VSAV1", null, null));
			moyens.add(new Moyen("VSAV2", null, null));
			
			casernes_list.add(new Caserne("C_"+i, moyens));
		}
		return casernes_list;
	}
	
	
	public String toString() {
		return "Sdis [casernes_list=" + caserne_list + ", interventions_list=" + intervention_list + "]";
	}
	
	
}
