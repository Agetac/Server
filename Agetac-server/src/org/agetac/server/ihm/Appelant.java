package org.agetac.server.ihm;

import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Appelant extends JPanel {
	// déclaration des éléments du Panel "Appelant"
	private TitledBorder titledborder = new TitledBorder("Appelant");
	private JLabel lnom = new JLabel("Nom: ");
	private JTextField tnom = new JTextField(10);
	private JLabel lnumero = new JLabel("Numéro: ");
	private JTextField tnumero = new JTextField(15);
	private JButton rappeler = new JButton("Rappeler", new ImageIcon("./ressources/PhoneIcon.gif"));
	private JLabel lappels = new JLabel("Appels: ");
	private JTextField tappels = new JTextField(3);

	public Appelant() {
		new JPanel();
		add(lappels);
		add(tappels);
		add(lnumero);
		add(tnumero);
		add(rappeler);
		add(lnom);
		add(tnom);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setBorder(titledborder);
	}
}
