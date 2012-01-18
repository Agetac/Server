package org.agetac.server.ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Engins extends JPanel {
	private TitledBorder titledborder = new TitledBorder("Engins retenus");

	public Engins() {
		new JPanel();
		JScrollPane barre1 = new JScrollPane();
		JScrollPane barre2 = new JScrollPane();
		this.add(barre1, BorderLayout.EAST);
		this.add(barre2, BorderLayout.SOUTH);
		this.setBorder(titledborder);
	}

}
