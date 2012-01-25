package org.agetac.server.ihm;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JFrame;

public class AgetacServerGUI extends JFrame{
	
	private static final long serialVersionUID = 2930978990073675343L;


	public AgetacServerGUI(){
		
	super();
	
	setTitle("Agetac Server");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	AgetacServerGUI_Lieu lieu = new AgetacServerGUI_Lieu();
	AgetacServerGUI_Moyens moyens = new AgetacServerGUI_Moyens();
	
	Box vBox = Box.createVerticalBox();
	vBox.add(lieu);
	vBox.add(moyens);
	add(vBox, BorderLayout.CENTER);
	
	pack();
	
	}
	


	public static void main(String[] args) {
		new AgetacServerGUI().setVisible(true);
	}

}
