package org.agetac.server.ihm;

import javax.swing.Box;
import javax.swing.JFrame;

public class CreationIntervention extends JFrame {
	
	private static final long serialVersionUID = -5965496066325257420L;
	//private Alerte alerte;
	private Adresse adresse;
	private Engins engins;
	private SolType soltype;
	private SolAdoptee soladoptee;
	private ListeDefense listedefense;
	private Recherche recherche;
	private CreationInterventionButtons buttons;

	public CreationIntervention(Alerte a) {
		new JFrame();
		//alerte = a;
		adresse = new Adresse();
		engins = new Engins();
		soltype = new SolType();
		soladoptee = new SolAdoptee();
		listedefense = new ListeDefense();
		recherche = new Recherche();
		buttons = new CreationInterventionButtons();
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
		
		setLocation(500,300);
		setSize(1024,780);
		
		this.add(vbox);
		this.setVisible(true);
		//this.setSize(getMaximumSize());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
