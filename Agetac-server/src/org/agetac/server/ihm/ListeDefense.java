package org.agetac.server.ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ListeDefense extends JPanel {
	private TitledBorder titledborder = new TitledBorder("Liste défense");

	public ListeDefense() {
		new JPanel();
		JScrollPane barre1 = new JScrollPane();
		this.add(barre1, BorderLayout.EAST);
		this.setBorder(titledborder);
	}

}
