package org.agetac.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import org.agetac.model.impl.Intervention;
import org.agetac.observer.Subject;

public class InterventionModel extends AbstractTableModel implements Observer{
	
	private List<Intervention> interventions;
	private final String[] entetes = { "ID", "Lieu", "Moyens", "Cibles","Sources", "Actions", "Messages", "Impliques" };
	
	public InterventionModel() {
		super();
		interventions = new ArrayList<Intervention>();
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
			return interventions.get(rowIndex).getUniqueID();
		case 1:
			return interventions.get(rowIndex).getPosition();
		case 2:
			return interventions.get(rowIndex).getVehicules();
		case 3:
			return interventions.get(rowIndex).getCibles();
		case 4:
			return interventions.get(rowIndex).getSources();
		case 5:
			return interventions.get(rowIndex).getActions();
		case 6:
			return interventions.get(rowIndex).getMessages();
		case 7:
			return interventions.get(rowIndex).getImpliques();
			
		default:
			return null; // Ne devrait jamais arriver
		}
	}

	public void addIntervention(Intervention inter) {
		interventions.add(inter);
		fireTableRowsInserted(interventions.size() - 1, interventions.size() - 1);
	}

	public void removeIntervention(int rowIndex) {
		interventions.remove(rowIndex);

		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	
	public void update(Subject s) {
		System.out.println("InterventionModel.update");
		//interventions = Interventions.getInstance().getInterventions(); // meme pb que pr agentModel
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
