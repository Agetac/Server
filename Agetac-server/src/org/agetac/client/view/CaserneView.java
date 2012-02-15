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

import org.agetac.client.controller.CaserneController;
import org.agetac.client.model.CaserneModel;

public class CaserneView extends JFrame {

	private CaserneController controller;
	private CaserneModel model;
	
	private JTable table;
	private JTextField txtId, txtNom, txtMoyens;
	private JButton addBut, delBut;


	public CaserneView(CaserneModel model) {
		
		this.model = model;
		this.controller = new CaserneController(this, this.model);

		// Conteneur principal
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// Tableau des casernes
		this.table = new JTable(this.model);
		
		// Tri tableau des casernes
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
		addBut = new JButton("Ajouter une caserne");
		addBut.addActionListener(this.controller);
		panelButton.add(addBut);

		// Supprimer
		delBut = new JButton("Supprimer une caserne");
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
				
		// Champ Moyens
		txtMoyens = new JTextField("Moyens");
		panelChamps.add(txtMoyens);
		
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
		setTitle("Liste des casernes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(true);
		
	}
	
	public String getID(){
		return txtId.getText();
	}
	
	public String getNom(){
		return txtNom.getText();
	}
	
	public String getMoyens(){
		return txtMoyens.getText();
	}
	
	public void resetTxtFields(){
		txtId.setText("ID");
		txtNom.setText("Nom");
		txtMoyens.setText("Moyens");
	}
	
	public int getSelectedLine(){
		return table.getSelectedRow();
	}
	
}
