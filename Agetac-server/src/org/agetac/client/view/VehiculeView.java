package org.agetac.client.view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.agetac.client.controler.VehiculeControler;
import org.agetac.client.model.VehiculeModel;

public class VehiculeView extends JFrame {

	private VehiculeControler controler;
	private VehiculeModel model;
	
	private JTable table;

	public VehiculeView(VehiculeModel model) {
		
		this.model = model;
		this.controler = new VehiculeControler(this, this.model);

		// Conteneur principal
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// Tableau des agents
		this.table = new JTable(this.model);

		// Les boutons
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());

		// Ajouter
		JButton addBut = new JButton("Ajouter un vehicule");
		addBut.addActionListener(this.controler);
		panel2.add(addBut);

		// Supprimer
		JButton delBut = new JButton("Supprimer un vehicule");
		delBut.addActionListener(this.controler);
		panel2.add(delBut);
		
		//ajout du panel
        panel.add(panel2,BorderLayout.CENTER);
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
