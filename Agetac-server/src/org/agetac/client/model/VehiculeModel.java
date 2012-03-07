package org.agetac.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import org.agetac.model.impl.Vehicule;

public class VehiculeModel extends AbstractTableModel implements Observer {

	private List<Vehicule> vehicules;
	private final String[] entetes = { "ID", "Nom", "Position", "Caserne",
			"Etat", "Groupe" };

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
			return vehicules.get(rowIndex).getCaserneName();
		case 4:
			return vehicules.get(rowIndex).getEtat();
		case 5:
			return vehicules.get(rowIndex).getGroupe();
		default:
			return null; // Ne devrait jamais arriver
		}
	}

	public void addVehicule(Vehicule vec) {
		vehicules.add(vec);
		fireTableRowsInserted(vehicules.size() - 1, vehicules.size() - 1);
	}

	public void removeVehicule(int rowIndex) {
		if (rowIndex != -1) {
			vehicules.remove(rowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
