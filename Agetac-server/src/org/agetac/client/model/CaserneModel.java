package org.agetac.client.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import org.agetac.common.model.impl.Caserne;

public class CaserneModel extends AbstractTableModel {

	private List<Caserne> casernes;
	private final String[] entetes = { "ID", "Nom", "Moyens" };

	public CaserneModel() {
		super();
		casernes = new ArrayList<Caserne>();
	}

	public int getRowCount() {
		return casernes.size();
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
			return casernes.get(rowIndex).getUniqueID();
		case 1:
			return casernes.get(rowIndex).getName();
		case 2:
			return casernes.get(rowIndex).getVehicules();
		default:
			return null; // Ne devrait jamais arriver
		}
	}

	public void addCaserne(Caserne cas) {
		casernes.add(cas);
		fireTableRowsInserted(casernes.size() - 1, casernes.size() - 1);
	}

	public void removeCaserne(int rowIndex) {
		if (rowIndex != -1) {
			casernes.remove(rowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
		}
	}

}
