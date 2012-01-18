package org.agetac.server.ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class SolType extends JPanel {
	private TitledBorder titledborder = new TitledBorder("Solution type");

	public SolType() {
		new JPanel();
		JScrollPane barre1 = new JScrollPane();
		this.add(barre1, BorderLayout.EAST);
		this.setBorder(titledborder);
	}

}
