package org.agetac.client.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.agetac.common.client.AgetacClient;
import org.agetac.common.dto.VehicleDTO;

@SuppressWarnings("serial")
public class VehicleModel extends AbstractTableModel{

	private List<VehicleDTO> vehicles;
	private final String[] entetes = { "ID", "Nom", "Position", "Caserne",
			"Etat", "Groupe" };
	private AgetacClient client;
	private long interID;

	public VehicleModel(AgetacClient c, long interId) {
		client = c;
		interID = interId;
		vehicles = new ArrayList<VehicleDTO>(client.getVehicles(interID));
	}

	public int getRowCount() {
		return vehicles.size();
	}

	public int getColumnCount() {
		return entetes.length;
	}

	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return vehicles.get(rowIndex).getId();
		case 1:
			return vehicles.get(rowIndex).getName();
		case 2:
			return vehicles.get(rowIndex).getPosition();
		case 3:
			return vehicles.get(rowIndex).getBarrack().getName();
		case 4:
			return vehicles.get(rowIndex).getState();
		case 5:
			return vehicles.get(rowIndex).getGroup();
		default:
			return null; // Should never happen
		}
	}

	public void addVehicle(VehicleDTO vec) {
		// TODO create good vehicle demand	
		//client.addVehicleDemand(interID, vehicleDemand)
			vehicles.add(vec);
			fireTableRowsInserted(vehicles.size() - 1, vehicles.size() - 1);
	}

	public void removeVehicle(int rowIndex) {
		if (rowIndex != -1) {
			// TODO not sure about the rowIndex just after
			client.deleteVehicle(rowIndex);
				vehicles.remove(rowIndex);
				fireTableRowsDeleted(rowIndex, rowIndex);
		}
	}
}
