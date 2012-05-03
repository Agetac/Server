package org.agetac.client.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.agetac.common.client.AgetacClient;
import org.agetac.common.dto.BarrackDTO;

public class BarrackModel extends AbstractTableModel {

	private List<BarrackDTO> barracks;
	private final String[] entetes = { "ID", "Nom", "Moyens" };
	private AgetacClient client;
	private long interID;

	public BarrackModel(AgetacClient c, long interId) {
		client = c;
		interID = interId;
		barracks = new ArrayList<BarrackDTO>();
	}

	public int getRowCount() {
		return barracks.size();
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
			return barracks.get(rowIndex).getId();
		case 1:
			return barracks.get(rowIndex).getName();
		case 2:
			return barracks.get(rowIndex); // not good yet
		default:
			return null; // Ne devrait jamais arriver
		}
	}

	public void addBarrack(BarrackDTO cas) {
		barracks.add(cas);
		fireTableRowsInserted(barracks.size() - 1, barracks.size() - 1);
	}

	public void removeBarrack(int rowIndex) {
		if (rowIndex != -1) {
			barracks.remove(rowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
		}
	}

}
