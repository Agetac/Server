package ihmserveur;

import javax.swing.Box;
import javax.swing.JFrame;


public class Alerte2 extends JFrame{
	private Alerte alerte;
	private Adresse adresse;
	private Engins engins;
	private SolType soltype;
	private SolAdoptee soladoptee;
	private ListeDefense listedefense;
	private Recherche recherche;
	private Alerte2Buttons buttons;

	public Alerte2(Alerte a){
		new JFrame();
		alerte = a;
		adresse = new Adresse();
		engins = new Engins();
		soltype = new SolType();
		soladoptee = new SolAdoptee();
		listedefense = new ListeDefense();
		recherche = new Recherche();
		buttons = new Alerte2Buttons();
		Box hbox = Box.createHorizontalBox();
		hbox.add(soltype);
		hbox.add(soladoptee);
		hbox.add(listedefense);
		Box vbox = Box.createVerticalBox();
		vbox.add(adresse);
		vbox.add(engins);
		vbox.add(hbox);
		vbox.add(recherche);
		vbox.add(buttons);
		this.add(vbox);
		this.setVisible(true);
		this.setSize(getMaximumSize());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
