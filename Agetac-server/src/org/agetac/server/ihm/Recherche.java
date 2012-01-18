package org.agetac.server.ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.Box;
import javax.swing.border.TitledBorder;

public class Recherche extends JPanel {
	private JLabel ltype = new JLabel("type: ");
	private JTextField ttype = new JTextField(3);
	private JButton btype = new JButton("...");
	private JLabel lnum = new JLabel("Num: ");
	private JTextField tnum = new JTextField(3);
	private JLabel lnbr = new JLabel("Nbr: ");
	private JTextField tnbr = new JTextField(3);
	private JLabel lcentre = new JLabel("Centre: ");
	private JTextField tcentre = new JTextField(10);
	private JButton bcentre = new JButton("...");
	private JButton bajout = new JButton("ajout");
	private JButton brecherche = new JButton("Rech. Engin");
	private TitledBorder titledborder = new TitledBorder("Recherche d'engin");

	public Recherche() {
		Box hbox1 = Box.createHorizontalBox();
		Box hbox2 = Box.createHorizontalBox();
		Box vbox = Box.createVerticalBox();
		Box vbox2 = Box.createVerticalBox();
		hbox1.add(ltype);
		hbox1.add(ttype);
		hbox1.add(btype);
		hbox1.add(lnum);
		hbox1.add(tnum);
		hbox1.add(lnbr);
		hbox1.add(tnbr);
		hbox2.add(lcentre);
		hbox2.add(tcentre);
		hbox2.add(bcentre);
		vbox.add(bajout);
		vbox.add(brecherche);
		vbox2.add(hbox1);
		vbox2.add(hbox2);
		new JPanel();
		add(vbox2, BorderLayout.CENTER);
		add(vbox, BorderLayout.EAST);
		setBorder(titledborder);
	}
}
