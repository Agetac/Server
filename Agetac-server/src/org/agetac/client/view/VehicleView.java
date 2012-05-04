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

import org.agetac.client.controller.VehicleController;
import org.agetac.client.model.VehicleModel;

@SuppressWarnings("serial")
public class VehicleView extends JFrame {

	private VehicleController controller;
	private VehicleModel model;
	
	private JTable table;
	private JTextField txtId, txtName, txtPosition, txtBarrack,txtState,txtGroup;
	private JButton addBut, delBut;

	public VehicleView(VehicleModel model) {
		
		this.model = model;
		this.controller = new VehicleController(this, this.model);

		// Main panel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// Vehicles table
		this.table = new JTable(this.model);
		
		// Vehicles table sort
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());   
		sorter.setSortable(0, true);
		sorter.setSortable(1, false);
		sorter.setSortable(2, false);
		sorter.setSortsOnUpdates(true);
		table.setRowSorter(sorter);
		table.getRowSorter().toggleSortOrder(0);

		// Buttons
		
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new GridLayout());

		// Add
		addBut = new JButton("Ajouter vehicule");
		addBut.addActionListener(this.controller);
		panelButton.add(addBut);

		// Delete
		delBut = new JButton("Effacer vehicule");
		delBut.addActionListener(this.controller);
		panelButton.add(delBut);
		
		

		// Fields
		JPanel panelChamps = new JPanel();
		panelChamps.setLayout(new GridLayout());
		
		// ID field
		txtId = new JTextField("ID");
		panelChamps.add(txtId);
		
		// Name field
		txtName = new JTextField("Nom");
		panelChamps.add(txtName);
		
		// Position field
		txtPosition = new JTextField("Position");
		panelChamps.add(txtPosition);

		// BarrackName field
		txtBarrack = new JTextField("Caserne");
		panelChamps.add(txtBarrack);

		// State field
		txtState = new JTextField("Etat");
		panelChamps.add(txtState);

		// Group field
		txtGroup = new JTextField("Groupe");
		panelChamps.add(txtGroup);

		// Fields & Buttons panel
		JPanel FandB = new JPanel();
		FandB.setLayout(new BorderLayout());
		FandB.add(panelButton,BorderLayout.NORTH);
        FandB.add(panelChamps,BorderLayout.SOUTH);

		
		// Add table, F&B to the main panel
		panel.add(new JScrollPane(table),BorderLayout.CENTER);
        panel.add(FandB,BorderLayout.SOUTH);
		setContentPane(panel);
		
		
		// JFrame config
		setTitle("Liste des vehicles");
		pack();
		setVisible(true);
		
	}
	
	
	public String getID(){
		return txtId.getText();
	}
	
	public String getName(){
		return txtName.getText();
	}
	
	public String getPosition(){
		return txtPosition.getText();
	}
	
	public String getBarrack(){
		return txtBarrack.getText();
	}
	
	public String getStates(){
		return txtState.getText();
	}
	
	public String getGroup(){
		return txtGroup.getText();
	}
	
	public void resetTxtFields(){
		txtId.setText("ID");
		txtName.setText("Nom");
		txtPosition.setText("Position");
		txtBarrack.setText("Caserne");
		txtState.setText("Etat");
		txtGroup.setText("Groupe");
	}
	
	public int getSelectedLine(){
		return table.getSelectedRow();
	}
	
	
	
}
