package org.agetac.client.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.agetac.common.client.AgetacClient;
import org.agetac.common.dto.BarrackDTO;
import org.agetac.common.dto.PositionDTO;
import org.agetac.common.dto.VehicleDTO;
import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.common.dto.VehicleDTO.VehicleState;
import org.agetac.common.dto.VehicleDTO.VehicleType;
import org.agetac.common.dto.VehicleDemandDTO.DemandState;

public class VehicleDemandModel extends AbstractTableModel {

	private List<VehicleDemandDTO> VehicleDemands;
	private final String[] entetes = { "Date", "Type du Vehicule", "Etat", "Position" };
	private AgetacClient client;
	private long interID;

	public VehicleDemandModel(AgetacClient c, long interId) {
		client = c;
		interID = interId;
		VehicleDemands = new ArrayList<VehicleDemandDTO>();
		c.getVehicleDemands(interID);
	}

	public int getRowCount() {
		return VehicleDemands.size();
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
			return VehicleDemands.get(rowIndex).getTimestamp();
		case 1:
			return VehicleDemands.get(rowIndex).getType();
		case 2:
			return VehicleDemands.get(rowIndex).getState();
		case 3:
			return VehicleDemands.get(rowIndex).getPosition();
		default:
			return null; // Should never happen
		}
	}

	public void updateVehicleDemand(VehicleDemandDTO dem) {
		// Si la demande est accepté, on ajoute un vehicule
		if(dem.getState()==DemandState.ACCEPTED){
			VehicleDTO v = new VehicleDTO("veh", VehicleState.ALERTE, dem.getType(), dem.getPosition(), null);
			v.setDemandTime(dem.getGroupeHoraire());
			
			v = client.addVehicle(interID, v);
			dem.setVehicleId((int)v.getId());//TODO : les ID sont en float -> modifier vehicleId
		}
		client.updateVehicleDemand(dem);
		this.fireTableDataChanged();
		//fireTableRowsInserted(VehicleDemands.size() - 1, VehicleDemands.size() - 1);
	}
	
	public VehicleDemandDTO getVehicleDemand(int index){
		return VehicleDemands.get(index);		
	}

	public void update() {
		VehicleDemands = new ArrayList<VehicleDemandDTO>(client.getVehicleDemands(interID));
		this.fireTableDataChanged();
	}

}
