package ihmserveur;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Listedefense extends JPanel{
	private TitledBorder titledborder= new TitledBorder("Liste défense");
	
	public Listedefense(){
		new JPanel();
		JScrollPane barre1 = new JScrollPane();
		this.add(barre1, BorderLayout.EAST);
		this.setBorder(titledborder);
	}


}
