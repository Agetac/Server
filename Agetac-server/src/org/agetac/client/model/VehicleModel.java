package org.agetac.client.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.agetac.common.client.AgetacClient;
import org.agetac.common.dto.VehicleDTO;
import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.common.dto.VehicleDTO.VehicleState;
import org.agetac.common.dto.VehicleDemandDTO.DemandState;

@SuppressWarnings("serial")
public class VehicleModel extends AbstractTableModel {

	private List<VehicleDTO> vehicles;
	private final String[] entetes = { "ID", "Nom", "Position",	"Etat" };
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
			return vehicles.get(rowIndex).getState();
			
		default:
			return null; // Should never happen
		}
	}



	public void updateVehicle(int rowIndex, VehicleDTO vec) {
		client.updateVehicle(vec);
		//this.fireTableDataChanged();
		//fireTableRowsInserted(vehicles.size() - 1, vehicles.size() - 1);
	}

	public VehicleDTO getVehicle(int rowIndex) {
		return vehicles.get(rowIndex);
	}
	
	public void addVehicle(VehicleDTO v){
		client.addVehicle(interID, v);
	}
	
	public void update() {
		vehicles = new ArrayList<VehicleDTO>(client.getVehicles(interID));
		this.fireTableDataChanged();
	}


}
