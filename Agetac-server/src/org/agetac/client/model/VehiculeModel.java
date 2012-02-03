package org.agetac.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import org.agetac.model.impl.Vehicule;
import org.agetac.observer.Subject;

public class VehiculeModel extends AbstractTableModel implements Observer{
	
	private List<Vehicule> vehicules;
	private final String[] entetes = { "ID", "Nom", "Position", "Etat", "Groupe"};
	
	public VehiculeModel() {
		super();
		vehicules = new ArrayList<Vehicule>();
	}

	public int getRowCount() {
		return vehicules.size();
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
			return vehicules.get(rowIndex).getUniqueID();
		case 1:
			return vehicules.get(rowIndex).getName();
		case 2:
			return vehicules.get(rowIndex).getPosition();
		case 3:
			return vehicules.get(rowIndex).getEtat();
		case 4:
			return vehicules.get(rowIndex).getGroupe();
		default:
			return null; // Ne devrait jamais arriver
		}
	}

	public void addVehicule(Vehicule cas) {
		vehicules.add(cas);
		fireTableRowsInserted(vehicules.size() - 1, vehicules.size() - 1);
	}

	public void removeVehicule(int rowIndex) {
		vehicules.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	
	public void update(Subject s) {
		System.out.println("VehiculeModel.update");
		//Vehicules = Vehicules.getInstance().getVehicules(); // meme pb que pr agentModel
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
