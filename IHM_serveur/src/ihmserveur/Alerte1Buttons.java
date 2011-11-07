package ihmserveur;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Alerte1Buttons extends JPanel {
	
	private JButton bProposer = new JButton("Proposer");
	private JButton bTransferer = new JButton("Transferer");
	private JButton bParquer = new JButton("Parquer");
	private JButton bDifferer = new JButton("Differer");
	private JButton bMultiple = new JButton("Multiple");
	private JButton bCTACODIS = new JButton("CTA/CODIS");
	private JButton bDebordement = new JButton("Debordement");
	private JButton bAbandon = new JButton("Abandon");
	

	public Alerte1Buttons(){
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
