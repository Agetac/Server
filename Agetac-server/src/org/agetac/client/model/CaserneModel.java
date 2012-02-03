package org.agetac.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import org.agetac.model.impl.Caserne;
import org.agetac.observer.Subject;

public class CaserneModel extends AbstractTableModel implements Observer{
	
	private List<Caserne> casernes;
	private final String[] entetes = { "ID", "Nom", "Moyens"};
	
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
		casernes.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	
	public void update(Subject s) {
		System.out.println("CaserneModel.update");
		//casernes = Casernes.getInstance().getCasernes(); // meme pb que pr agentModel
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
