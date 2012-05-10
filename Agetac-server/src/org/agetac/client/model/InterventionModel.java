package org.agetac.client.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.agetac.common.client.AgetacClient;
import org.agetac.common.dto.InterventionDTO;

@SuppressWarnings("serial")
public class InterventionModel extends AbstractTableModel {

	private List<InterventionDTO> interventions;
	private final String[] entetes = {
			"ID", "Position (lat,long)", "Vehicules", "Cibles",
			"Sources", "Actions", "Messages", "Victimes"};
	
	private AgetacClient client;
	

	public InterventionModel(AgetacClient c) {
		client=c;
		interventions = new ArrayList<InterventionDTO>(client.getInterventions());
	}
	
	public int getRowCount() {
		return interventions.size();
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
			return interventions.get(rowIndex).getId();
		case 1:
			return "("+ interventions.get(rowIndex).getPosition().getLatitude() +", "+ interventions.get(rowIndex).getPosition().getLongitude() +")";
		case 2:
			return interventions.get(rowIndex).getVehicles().size();
		case 3:
			return interventions.get(rowIndex).getDemands().size();
		case 4:
			return interventions.get(rowIndex).getTargets().size();
		case 5:
			return interventions.get(rowIndex).getSources().size();
		case 6:
			return interventions.get(rowIndex).getActions().size();
		case 7:
			return interventions.get(rowIndex).getMessages().size();
		case 8:
			return interventions.get(rowIndex).getVictims().size();

		default:
			return null; // Should never happen
		}
	}

	public void addIntervention(InterventionDTO inter) {
		interventions.add(inter);
		fireTableRowsInserted(interventions.size() - 1,	interventions.size() - 1);
	}
	
	public InterventionDTO getInter (int num){
		return interventions.get(num);
	}
	
	public AgetacClient getClient(){
		return client;
	}
	
	public void update(){
		interventions = new ArrayList<InterventionDTO>(client.getInterventions());
		this.fireTableDataChanged();
	}

}
