package org.agetac.client.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.agetac.client.controller.InterventionController;
import org.agetac.client.model.InterventionModel;
import org.agetac.common.dto.PositionDTO;

@SuppressWarnings("serial")
public class InterventionView extends JFrame {

	private InterventionController controller;
	private InterventionModel model;

	private JTable table;
	private JTextField txtName, txtLatitude, txtLongitude;

	private JComboBox<String> typeInter = new JComboBox<String>();
	
	private JButton addBut, detailBut;

	public InterventionView(InterventionModel model) {

		this.model = model;
		this.controller = new InterventionController(this, this.model);

		// Main panel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// Interventions table
		this.table = new JTable(this.model);

		// Interventions table sort
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				table.getModel());
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
		addBut = new JButton("Ajouter intervention");
		addBut.addActionListener(this.controller);
		panelButton.add(addBut);

		// Details
		detailBut = new JButton("Intervention details");
		detailBut.addActionListener(this.controller);
		panelButton.add(detailBut);

		// Fields
		JPanel panelChamps = new JPanel();
		panelChamps.setLayout(new GridLayout());

		// Name field
		txtName = new JTextField("Nom");
		panelChamps.add(txtName);

		// Latitude field
		txtLatitude = new JTextField("Latitude");
		panelChamps.add(txtLatitude);

		// Longitude field
		txtLongitude = new JTextField("Longitude");
		panelChamps.add(txtLongitude);
		
		typeInter.setPreferredSize(new Dimension(100,20));
		typeInter.addItem("Feu habitation"); // 2 FPT / 1 VL
		typeInter.addItem("Feu établissement recevant du public"); // 2FPT / 1EPA / 1 VSAB / 1VL / 1VSR
		typeInter.addItem("Carambolage"); // 1FPT / 4VSAB / 2VSR / 1VL 

		panelChamps.add(typeInter);

		// Fields & Buttons panel
		JPanel FandB = new JPanel();
		FandB.setLayout(new BorderLayout());
		FandB.add(panelButton, BorderLayout.NORTH);
		FandB.add(panelChamps, BorderLayout.SOUTH);

		// Add table & F&B to the main panel
		panel.add(new JScrollPane(table), BorderLayout.CENTER);
		panel.add(FandB, BorderLayout.SOUTH);
		setContentPane(panel);

		// Config de la JFrame
		setTitle("Liste des interventions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(true);

	}

	public String getName() {
		return txtName.getText();
	}

	public PositionDTO getPosition() {
		String lat = txtLatitude.getText();
		String longi = txtLongitude.getText();
		PositionDTO pos = new PositionDTO(Integer.parseInt(lat),
				Integer.parseInt(longi));
		return pos;
	}

	public void resetTxtFields() {
		txtName.setText("Nom");
		txtLatitude.setText("Latitude");
		txtLongitude.setText("Longitude");
	}

	public int getSelectedLine() {
		return table.getSelectedRow();
	}

	public void showError(String message) {
		JOptionPane.showMessageDialog(this, message);
		
	}
	
	public int getCodeSinistre() {
		if(this.typeInter.getSelectedItem().equals("Feu habitation")){
			return 0;
		}
		if(this.typeInter.getSelectedItem().equals("Feu habitation a étage")){
			return 1;
		}
		if(this.typeInter.getSelectedItem().equals("Feu établissement recevant du public")){
			return 2;
		}
		if(this.typeInter.getSelectedItem().equals("Carambolage")){
			return 3;
		}
		return -1;
	}

}
