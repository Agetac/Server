package org.agetac.client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.agetac.client.controller.CaserneController;
import org.agetac.client.model.CaserneModel;

public class CaserneView extends JFrame {

	private CaserneController controller;
	private CaserneModel model;
	
	private JTable table;

	public CaserneView(CaserneModel model) {
		
		this.model = model;
		this.controller = new CaserneController(this, this.model);

		// Conteneur principal
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// Tableau des casernes
		this.table = new JTable(this.model);
		
		// Les boutons
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());

		// Ajouter
		JButton addBut = new JButton("Ajouter une caserne");
		addBut.addActionListener(this.controller);
		panel2.add(addBut);

		// Supprimer
		JButton delBut = new JButton("Supprimer une caserne");
		delBut.addActionListener(this.controller);
		panel2.add(delBut);
		
		//ajout de la table et du panel des boutons
		panel.add(new JScrollPane(table),BorderLayout.CENTER);
        panel.add(panel2,BorderLayout.SOUTH);
		setContentPane(panel);
		
		
		// Config de la JFrame
		setTitle("Liste des casernes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(true);
		
	}
	
	
	public void refreshCasernes(){
			
	}
	
	
}
