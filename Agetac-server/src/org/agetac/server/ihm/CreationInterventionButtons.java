package org.agetac.server.ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;

public class CreationInterventionButtons extends JPanel {

	private static final long serialVersionUID = 1913062583786569222L;

	private JButton bDiffuser = new JButton("Diffuser");
	private JButton bActions = new JButton("Actions");
	private JButton bAlerte = new JButton("Alerte");
	private JButton bProposer = new JButton("Proposer");
	private JButton bAbandon = new JButton("Abandon");

	public CreationInterventionButtons() {
		new JPanel();

		Box h1 = Box.createHorizontalBox();

		h1.add(bDiffuser);
		h1.add(bActions);
		h1.add(bAlerte);
		h1.add(bProposer);
		h1.add(bAbandon);
		add(h1, BorderLayout.CENTER);

		setLayout(new FlowLayout(FlowLayout.CENTER));

	}
}
