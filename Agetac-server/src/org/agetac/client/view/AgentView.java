package org.agetac.client.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.agetac.client.controller.AgentController;
import org.agetac.client.model.AgentModel;

public class AgentView extends JFrame {

	private AgentController controller;
	private AgentModel model;
	
	private JTable table;
	private JTextField txtId, txtNom, txtAptitudes, txtSubordonnes;
	private JButton addBut, delBut;

	public AgentView(AgentModel model) {
		
		this.model = model;
		this.controller = new AgentController(this, this.model);

		// Conteneur principal
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// Tableau des agents
		this.table = new JTable(this.model);
		
		// Tri tableau des agents
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());   
				sorter.setSortable(0, true);
				sorter.setSortable(1, false);
				sorter.setSortable(2, false);
				sorter.setSortsOnUpdates(true);
				table.setRowSorter(sorter);
				table.getRowSorter().toggleSortOrder(0);

		// Les boutons
		
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new GridLayout());

		// Ajouter
		addBut = new JButton("Ajouter un agent");
		addBut.addActionListener(this.controller);
		panelButton.add(addBut);

		// Supprimer
		delBut = new JButton("Supprimer un agent");
		delBut.addActionListener(this.controller);
		panelButton.add(delBut);
		
		//Champs
		JPanel panelChamps = new JPanel();
		panelChamps.setLayout(new GridLayout());
				
		// Champ ID
		txtId = new JTextField("ID");
		panelChamps.add(txtId);
				
		// Champ Nom
		txtNom = new JTextField("Nom");
		panelChamps.add(txtNom);
				
		// Champ Aptitudes
		txtAptitudes = new JTextField("Aptitudes");
		panelChamps.add(txtAptitudes);

		// Champ Subordonnes
		txtSubordonnes = new JTextField("Subordonnes");
		panelChamps.add(txtSubordonnes);

		// Panel Champs & Buttons
		JPanel CandB = new JPanel();
		CandB.setLayout(new BorderLayout());
		CandB.add(panelButton,BorderLayout.NORTH);
		CandB.add(panelChamps,BorderLayout.SOUTH);
		
		
		//ajout de la table et du panel des boutons
		panel.add(new JScrollPane(table),BorderLayout.CENTER);
        panel.add(CandB,BorderLayout.SOUTH);
		setContentPane(panel);
		
		
		// Config de la JFrame
		setTitle("Liste des agents");
		pack();
		setVisible(true);
		
	}
	
	public String getID(){
		return txtId.getText();
	}
	
	public String getNom(){
		return txtNom.getText();
	}
	
	public String getAptitudes(){
		return txtAptitudes.getText();
	}
	
	public String getSubordonnes(){
		return txtSubordonnes.getText();
	}
	
	public void resetTxtFields(){
		txtId.setText("ID");
		txtNom.setText("Nom");
		txtAptitudes.setText("Aptitudes");
		txtSubordonnes.setText("Subordonnes");
	}
	
	public int getSelectedLine(){
		return table.getSelectedRow();
	}
	
	
}
