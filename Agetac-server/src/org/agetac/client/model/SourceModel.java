package org.agetac.client.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.agetac.common.client.AgetacClient;
import org.agetac.common.dto.SourceDTO;

public class SourceModel extends AbstractTableModel {

	private List<SourceDTO> sources;
	private final String[] entetes = { "ID", "Position", "Type" };
	private AgetacClient client;
	private long interID;

	public SourceModel(AgetacClient c, long interId) {
		client = c;
		interID = interId;
		sources = (List<SourceDTO>) client.getSources(interID);
		// may crash because of the cast...
	}

	public int getRowCount() {
		return sources.size();
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
			return sources.get(rowIndex).getId();
		case 1:
			return sources.get(rowIndex).getPosition();
		case 2:
			return sources.get(rowIndex).getType();
		default:
			return null; // Ne devrait jamais arriver
		}
	}

	public void addSource(SourceDTO src) {
		client.addSource(interID, src);
		sources.add(src);
		fireTableRowsInserted(sources.size() - 1, sources.size() - 1);
	}

	public void removeSource(int rowIndex) {
		if (rowIndex != -1) {
			client.deleteSource(rowIndex);
			sources.remove(rowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
		}
	}
}
