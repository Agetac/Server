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
	private JTextField txtType, txtName;
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
		addBut = new JButton("Ajouter");
		addBut.addActionListener(this.controller);
		panelButton.add(addBut);

		// Delete
		delBut = new JButton("Faire rentrer");
		delBut.addActionListener(this.controller);
		panelButton.add(delBut);
		
		

		// Fields
		JPanel panelChamps = new JPanel();
		panelChamps.setLayout(new GridLayout());
		
		// Name field
		txtName = new JTextField("Nom");
		panelChamps.add(txtName);

		// Type field
		txtType = new JTextField("Type");
		panelChamps.add(txtType);


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
	
	public String getName(){
		return txtName.getText();
	}
	
	public String getVehType(){
		return txtType.getText();
	}
	
	public void resetTxtFields(){
		
		txtName.setText("Nom");
		txtType.setText("Type");
	}
	
	public int getSelectedLine(){
		return table.getSelectedRow();
	}
	
	
	
}
