package org.agetac.server.ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PriseAlerteButtons extends JPanel {
	
	private JButton bProposer = new JButton("Proposer");
	private JButton bTransferer = new JButton("Transférer");
	private JButton bParquer = new JButton("Parquer");
	private JButton bDifferer = new JButton("Différer");
	private JButton bMultiple = new JButton("Multiple");
	private JButton bCTACODIS = new JButton("CTA/CODIS");
	private JButton bDebordement = new JButton("Débordement");
	private JButton bAbandon = new JButton("Abandon");
	

	public PriseAlerteButtons(){
		new JPanel();
		
		Box h1 = Box.createHorizontalBox();
		Box h2 = Box.createHorizontalBox();
		Box vbox = Box.createVerticalBox();
		
		h1.add(bProposer);
		h1.add(bTransferer);
		h1.add(bParquer);
		h1.add(bDifferer);
		h1.add(bMultiple);
		h2.add(bCTACODIS);
		h2.add(bDebordement);
		h2.add(bAbandon);
		vbox.add(h1);
		vbox.add(h2);
		add(vbox,BorderLayout.CENTER);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));

	}
}
