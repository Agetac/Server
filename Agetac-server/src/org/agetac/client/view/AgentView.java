package org.agetac.client.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.agetac.client.controler.AgentControler;
import org.agetac.client.model.AgentModel;

public class AgentView extends JFrame {

	private AgentControler controler;
	private AgentModel model;
	
	private JTable table;

	public AgentView(AgentModel model) {
		
		this.model = model;
		this.controler = new AgentControler(this, this.model);

		// Conteneur principal
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// Tableau des agents
		this.table = new JTable(this.model);

		// Les boutons
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());

		// Ajouter
		JButton addBut = new JButton("Ajouter un agent");
		addBut.addActionListener(this.controler);
		panel2.add(addBut);

		// Supprimer un message
		JButton delBut = new JButton("Supprimer un agent");
		delBut.addActionListener(this.controler);
		panel2.add(delBut);
		
		//ajout du panel
        panel.add(panel2,BorderLayout.CENTER);
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
