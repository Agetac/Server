package ihmserveur;

import javax.swing.JFrame;


public class Alerte {
	private alerte1 al1;
	private alerte2 al2;


	public Alerte(){
		al1 = new alerte1(this);
		al2 = new alerte2(this);
	}


	public static void main(String[] args){
		new Alerte();
	}	
}