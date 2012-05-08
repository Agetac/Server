package org.agetac.client.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.agetac.client.controller.VehicleDemandController;
import org.agetac.client.model.VehicleDemandModel;
import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.common.dto.VehicleDemandDTO.DemandState;

public class VehicleDemandView extends JFrame {

	private VehicleDemandController controller;
	private VehicleDemandModel model;

	private JTable table;
	private JButton okBut, koBut;

	public VehicleDemandView(VehicleDemandModel model) {

		this.model = model;
		this.controller = new VehicleDemandController(this, this.model);

		// Main Panel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// VehicleDemand table
		this.table = new JTable(this.model);

		// VehicleDemand table sort
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

		
		//Accept
		okBut = new JButton("Accepter");
		okBut.addActionListener(this.controller);
		panelButton.add(okBut);
		
		//Refuse
		koBut = new JButton("Refuser");
		koBut.addActionListener(this.controller);
		panelButton.add(koBut);



		// Fields & Buttons Panel
		JPanel CandB = new JPanel();
		CandB.setLayout(new BorderLayout());
		CandB.add(panelButton, BorderLayout.NORTH);

		// Add table, Fiels&Button panel to main panel
		panel.add(new JScrollPane(table), BorderLayout.CENTER);
		panel.add(CandB, BorderLayout.SOUTH);
		setContentPane(panel);

		// JFrame config
		setTitle("Liste des demandes de vehicules");
		pack();
		setVisible(true);

	}

	public int getSelectedLine() {
		return table.getSelectedRow();
	}

}
