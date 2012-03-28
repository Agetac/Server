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

import org.agetac.client.controller.InterventionController;
import org.agetac.client.model.InterventionModel;
import org.agetac.common.model.impl.Position;

public class InterventionView extends JFrame {

	private InterventionController controller;
	private InterventionModel model;
	
	private JTable table;
	private JTextField txtName, txtLatitude, txtLongitude;
	private JButton addBut, delBut, seeBut;

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
		
		// Voir
		seeBut = new JButton("Voir une intervention");
		seeBut.addActionListener(this.controller);
		panelButton.add(seeBut);
		
		
		
		//Champs
		JPanel panelChamps = new JPanel();
		panelChamps.setLayout(new GridLayout());
				
		// Champ Name
		txtName = new JTextField("Name");
		panelChamps.add(txtName);
				
		// Champ Latitude
		txtLatitude = new JTextField("Latitude");
		panelChamps.add(txtLatitude);
		
		// Champ Longitude
		txtLongitude = new JTextField("Longitude");
		panelChamps.add(txtLongitude);

		
		
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
	
	
	public String getName(){
		return txtName.getText();
	}
	
	public Position getPosition(){
		String lat = txtLatitude.getText();
		String longi = txtLongitude.getText();
		Position pos = new Position(Double.parseDouble(lat),Double.parseDouble(longi));
		return pos;
	}
	
	
	public void resetTxtFields(){
		txtName.setText("Name");
		txtLatitude.setText("Latitude");
		txtLongitude.setText("Longitude");
	}
	
	public int getSelectedLine(){
		return table.getSelectedRow();
	}
	
	
}
