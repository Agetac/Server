package org.agetac.server.ihm;

import javax.swing.Box;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Adresse extends JPanel{
	private TitledBorder titledborder= new TitledBorder("Adresse");
	private JLabel ladresse = new JLabel("Adresse: ");
	private JTextField tadresse = new JTextField(10);
	private JLabel lsinistre = new JLabel("Sinistre: ");
	private JTextField tcodesinistre = new JTextField(3);
	private JTextField tnomsinistre = new JTextField(15);
	private JButton consignes = new JButton("Consignes");
	
	public Adresse(){
		new JPanel();
		Box hbox1 = Box.createHorizontalBox();
		Box hbox2 = Box.createHorizontalBox();
		Box vbox = Box.createVerticalBox();
		hbox1.add(ladresse);
		hbox1.add(tadresse);
		hbox2.add(lsinistre);
		hbox2.add(tcodesinistre);
		hbox2.add(tnomsinistre);
		vbox.add(hbox1);
		vbox.add(hbox2);
		vbox.setBorder(titledborder);
		add(vbox, BorderLayout.CENTER);
		add(consignes, BorderLayout.EAST);
	}
}
