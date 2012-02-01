package org.agetac.client.view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.agetac.client.controler.InterventionControler;
import org.agetac.client.model.InterventionModel;

public class InterventionView extends JFrame {

	private InterventionControler controler;
	private InterventionModel model;
	
	private JTable table;

	public InterventionView(InterventionModel model) {
		
		this.model = model;
		this.controler = new InterventionControler(this, this.model);

		// Conteneur principal
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// Tableau des agents
		this.table = new JTable(this.model);

		// Les boutons
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());

		// Ajouter
		JButton addBut = new JButton("Ajouter une intervention");
		addBut.addActionListener(this.controler);
		panel2.add(addBut);

		// Supprimer un message
		JButton delBut = new JButton("Supprimer une intervention");
		delBut.addActionListener(this.controler);
		panel2.add(delBut);
		
		//ajout du panel
        panel.add(panel2,BorderLayout.CENTER);
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
