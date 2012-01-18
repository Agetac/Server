package org.agetac.server.ihm;

import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class EnCours extends JPanel {
	private TitledBorder titledborder = new TitledBorder("Alertes et interventions en cours dans le secteur");

	public EnCours() {
		new JPanel();
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setBorder(titledborder);
	}

}
