package ihmserveur;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Soltype extends JPanel{
	private TitledBorder titledborder= new TitledBorder("Solution type");
	
	public Soltype(){
		new JPanel();
		JScrollPane barre1 = new JScrollPane();
		this.add(barre1, BorderLayout.EAST);
		this.setBorder(titledborder);
	}

}
