package org.agetac.client.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.agetac.common.client.AgetacClient;
import org.agetac.common.dto.AgentDTO;

public class AgentModel extends AbstractTableModel {

	private static final long serialVersionUID = -3436934299381693000L;

	private final List<AgentDTO> agents;
	private final String[] entetes = { "ID", "Nom", "Aptitude", "Subordonnes" };
	private AgetacClient client;
	private long interID;

	public AgentModel(AgetacClient c, long interId) {
		client = c;
		interID = interId;
		agents = new ArrayList<AgentDTO>();
	}

	public int getRowCount() {
		return agents.size();
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
			return agents.get(rowIndex).getId();
		case 1:
			return agents.get(rowIndex).getName();
		case 2:
			return agents.get(rowIndex); // TODO
		case 3:
			return agents.get(rowIndex); // TODO
		default:
			return null; // Ne devrait jamais arriver
		}
	}

	public void addAgent(AgentDTO ag) {
		agents.add(ag);
		fireTableRowsInserted(agents.size() - 1, agents.size() - 1);
	}

	public void removeAgent(int rowIndex) {
		if (rowIndex != -1) {
			agents.remove(rowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
		}
	}

}
