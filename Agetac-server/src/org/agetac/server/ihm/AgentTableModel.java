package org.agetac.server.ihm;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.agetac.common.Agent;
import org.agetac.common.Message;
import org.agetac.server.db.Agents;
import org.agetac.server.db.Messages;

public class AgentTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -3436934299381693000L;
	
	private final Collection<Agent> agents;
	private final String[] entetes = { "ID", "Nom", "Aptitude","Subordonnes" };

	public AgentTableModel() {
		super();
		agents = Agents.getInstance().getAgents();
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
			return agents.get(rowIndex).getNom();
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
		fireTableRowsInserted(agents.size() - 1, agents.size() - 1);
	}

	public void removeMessage(int rowIndex) {
		agents.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

}
