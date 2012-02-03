package org.agetac.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import org.agetac.model.impl.Agent;
import org.agetac.observer.Subject;

public class AgentModel extends AbstractTableModel implements Observer{
	
	private static final long serialVersionUID = -3436934299381693000L;
	
	private final List<Agent> agents;
	private final String[] entetes = { "ID", "Nom", "Aptitude","Subordonnes" };

	public AgentModel() {
		super();
		agents = new ArrayList<Agent>();
		//agents = InterventionConnection.getAgents();
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
			return agents.get(rowIndex).getUniqueId();
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
		// InterventionConnection.put(ag);
		fireTableRowsInserted(agents.size() - 1, agents.size() - 1);
	}

	public void removeAgent(int rowIndex) {
		agents.remove(rowIndex);
		// InterventionConnection.removerowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	
	public void update(Subject s) {
		System.out.println("AgentModel.update");
		//agents = Agents.getInstance().getAgents() // pb de collection en utilisant la db
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
