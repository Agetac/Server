package ihmserveur;



public class Alerte {
	private Alerte1 al1;
	private Alerte2 al2;


	public Alerte(){
		al1 = new Alerte1(this);
		al2 = new Alerte2(this);
	}


	public static void main(String[] args){
		new Alerte();
	}	
}