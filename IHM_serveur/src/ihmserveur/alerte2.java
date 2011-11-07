package ihmserveur;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.Box;


public class alerte2 extends JFrame{
	private Alerte alerte;
	private Adresse adresse;
	private Engins engins;
	private Soltype soltype;
	private Soladoptee soladoptee;
	private Listedefense listedefense;
	private Recherche recherche;
	private Alerte2Buttons buttons;

	public alerte2(Alerte a){
		new JFrame();
		alerte = a;
		adresse = new Adresse();
		engins = new Engins();
		soltype = new Soltype();
		soladoptee = new Soladoptee();
		listedefense = new Listedefense();
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
