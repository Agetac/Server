package org.agetac.server.ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class SolAdoptee extends JPanel {
	private TitledBorder titledborder = new TitledBorder("Solution adoptée");

	public SolAdoptee() {
		new JPanel();
		JScrollPane barre1 = new JScrollPane();
		this.add(barre1, BorderLayout.EAST);
		this.setBorder(titledborder);
	}

}
