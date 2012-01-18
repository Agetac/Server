package org.agetac.server.ihm;

import java.awt.*;

import javax.swing.Box;
import javax.swing.JFrame;


public class PriseAlerte extends JFrame{
	private Alerte alerte;
	private Appelant appelant;
	private Localisation localisation;
	private Sinistre sinistre;
	private EnCours encours;
	private PriseAlerteButtons buttons;
	

	public PriseAlerte(Alerte a){
		new JFrame();
		alerte = a;
		appelant = new Appelant();
		localisation = new Localisation();
		sinistre = new Sinistre();
		encours = new EnCours();
		buttons = new PriseAlerteButtons();
		Box vBox = Box.createVerticalBox();
		vBox.add(appelant);
		vBox.add(localisation);
		vBox.add(sinistre);
		vBox.add(encours);
		vBox.add(buttons);
		setLocation(500,300);
		setSize(1024,780);
		setTitle("Alerte");
		add(vBox, BorderLayout.CENTER);
		setVisible(true);
	}
}
