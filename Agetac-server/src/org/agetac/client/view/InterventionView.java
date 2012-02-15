package org.agetac.client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.agetac.client.controller.InterventionController;
import org.agetac.client.model.InterventionModel;

public class InterventionView extends JFrame {

	private InterventionController controller;
	private InterventionModel model;
	
	private JTable table;
	private JTextField txtId, txtLieu, txtMoyens, txtCibles, txtSources, txtActions, txtMessages, txtImpliques;
	private JButton addBut, delBut;

	public InterventionView(InterventionModel model) {
		
		this.model = model;
		this.controller = new InterventionController(this, this.model);

		// Conteneur principal
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// Tableau des interventions
		this.table = new JTable(this.model);
		
		// Tri tableau des interventions
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
		addBut = new JButton("Ajouter une intervention");
		addBut.addActionListener(this.controller);
		panelButton.add(addBut);

		// Supprimer
		delBut = new JButton("Supprimer une intervention");
		delBut.addActionListener(this.controller);
		panelButton.add(delBut);
		
		
		//Champs
		JPanel panelChamps = new JPanel();
		panelChamps.setLayout(new GridLayout());
				
		// Champ ID
		txtId = new JTextField("ID");
		panelChamps.add(txtId);
				
		// Champ Lieu
		txtLieu = new JTextField("Lieu");
		panelChamps.add(txtLieu);
				
		// Champ Moyens
		txtMoyens = new JTextField("Moyens");
		panelChamps.add(txtMoyens);
		
		// Champ Cibles
		txtCibles = new JTextField("Cibles");
		panelChamps.add(txtCibles);
		
		// Champ Sources
		txtSources = new JTextField("Sources");
		panelChamps.add(txtSources);
		
		// Champ Actions
		txtActions = new JTextField("Actions");
		panelChamps.add(txtActions);
			
		// Champ Messages
		txtMessages = new JTextField("Messages");
		panelChamps.add(txtMessages);
		
		// Champ Impliques
		txtImpliques = new JTextField("Impliques");
		panelChamps.add(txtImpliques);
		
		
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
		setTitle("Liste des interventions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(true);
		
	}
	
	
	public String getID(){
		return txtId.getText();
	}
	
	public String getLieu(){
		return txtLieu.getText();
	}
	
	public String getMoyens(){
		return txtMoyens.getText();
	}
	
	public String getCibles(){
		return txtCibles.getText();
	}
	
	public String getSources(){
		return txtSources.getText();
	}
	
	public String getActions(){
		return txtActions.getText();
	}
	
	public String getMessages(){
		return txtMessages.getText();
	}
	
	public String getImpliques(){
		return txtImpliques.getText();
	}
	
	public void resetTxtFields(){
		txtId.setText("ID");
		txtLieu.setText("Lieu");
		txtMoyens.setText("Moyens");
		txtCibles.setText("Cibles");
		txtSources.setText("Sources");
		txtActions.setText("Actions");
		txtMessages.setText("Messages");
		txtImpliques.setText("Impliques");
	}
	
	public int getSelectedLine(){
		return table.getSelectedRow();
	}
	
	
}
