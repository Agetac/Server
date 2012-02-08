package org.agetac.client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.agetac.client.controller.InterventionController;
import org.agetac.client.model.InterventionModel;

public class InterventionView extends JFrame {

	private InterventionController controller;
	private InterventionModel model;
	
	private JTable table;

	public InterventionView(InterventionModel model) {
		
		this.model = model;
		this.controller = new InterventionController(this, this.model);

		// Conteneur principal
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// Tableau des interventions
		this.table = new JTable(this.model);

		// Les boutons
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());

		// Ajouter
		JButton addBut = new JButton("Ajouter une intervention");
		addBut.addActionListener(this.controller);
		panel2.add(addBut);

		// Supprimer
		JButton delBut = new JButton("Supprimer une intervention");
		delBut.addActionListener(this.controller);
		panel2.add(delBut);
		
		//ajout de la table et du panel des boutons
		panel.add(new JScrollPane(table),BorderLayout.CENTER);
        panel.add(panel2,BorderLayout.SOUTH);
		setContentPane(panel);
		
		
		// Config de la JFrame
		setTitle("Liste des interventions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(true);
		
	}
	
	
	public void refreshInterventions(){
			
	}
	
	
}
