package org.agetac.server.ihm;



public class Alerte {
	private PriseAlerte al1;
	private CreationIntervention al2;


	public Alerte(){
		al1 = new PriseAlerte(this);
		al2 = new CreationIntervention(this);
	}


	public static void main(String[] args){
		new Alerte();
	}	
}