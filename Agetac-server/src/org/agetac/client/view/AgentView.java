package org.agetac.client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.agetac.client.controller.AgentController;
import org.agetac.client.model.AgentModel;

public class AgentView extends JFrame {

	private AgentController controller;
	private AgentModel model;
	
	private JTable table;

	public AgentView(AgentModel model) {
		
		this.model = model;
		this.controller = new AgentController(this, this.model);

		// Conteneur principal
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// Tableau des agents
		this.table = new JTable(this.model);

		// Les boutons
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());

		// Ajouter
		JButton addBut = new JButton("Ajouter un agent");
		addBut.addActionListener(this.controller);
		panel2.add(addBut);

		// Supprimer
		JButton delBut = new JButton("Supprimer un agent");
		delBut.addActionListener(this.controller);
		panel2.add(delBut);
		
		//ajout de la table et du panel des boutons
		panel.add(new JScrollPane(table),BorderLayout.CENTER);
        panel.add(panel2,BorderLayout.SOUTH);
		setContentPane(panel);
		
		
		// Config de la JFrame
		setTitle("Liste des agents");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(true);
		
	}
	
	
	public void refreshAgents(){
			
	}
	
	
}
