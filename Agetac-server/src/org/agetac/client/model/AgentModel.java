package org.agetac.client.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.agetac.common.model.impl.Agent;

public class AgentModel extends AbstractTableModel {

	private static final long serialVersionUID = -3436934299381693000L;

	private final List<Agent> agents;
	private final String[] entetes = { "ID", "Nom", "Aptitude", "Subordonnes" };

	public AgentModel() {
		super();
		agents = new ArrayList<Agent>();
		// agents = InterventionConnection.getAgents();
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
			return agents.get(rowIndex).getUniqueID();
		case 1:
			return agents.get(rowIndex).getName();
		case 2:
			return agents.get(rowIndex).getAptitude();
		case 3:
			return agents.get(rowIndex).getSubordonnes();
		default:
			return null; // Ne devrait jamais arriver
		}
	}

	public void addAgent(Agent ag) {
		agents.add(ag);
		// InterventionConnection.put(ag);
		fireTableRowsInserted(agents.size() - 1, agents.size() - 1);
	}

	public void removeAgent(int rowIndex) {
		if (rowIndex != -1) {
			agents.remove(rowIndex);
			// InterventionConnection.removerowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
		}
	}

}
