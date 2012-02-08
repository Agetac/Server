package org.agetac.client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.agetac.client.controller.VehiculeController;
import org.agetac.client.model.VehiculeModel;

public class VehiculeView extends JFrame {

	private VehiculeController controller;
	private VehiculeModel model;
	
	private JTable table;

	public VehiculeView(VehiculeModel model) {
		
		this.model = model;
		this.controller = new VehiculeController(this, this.model);

		// Conteneur principal
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// Tableau des vehicules
		this.table = new JTable(this.model);

		// Les boutons
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());

		// Ajouter
		JButton addBut = new JButton("Ajouter un vehicule");
		addBut.addActionListener(this.controller);
		panel2.add(addBut);

		// Supprimer
		JButton delBut = new JButton("Supprimer un vehicule");
		delBut.addActionListener(this.controller);
		panel2.add(delBut);
		
		//ajout de la table et du panel des boutons
		panel.add(new JScrollPane(table),BorderLayout.CENTER);
        panel.add(panel2,BorderLayout.SOUTH);
		setContentPane(panel);
		
		
		// Config de la JFrame
		setTitle("Liste des vehicules");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(true);
		
	}
	
	
	public void refreshVehicules(){
			
	}
	
	
}
