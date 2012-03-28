package org.agetac.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import org.agetac.server.db.Interventions;

import org.agetac.client.api.ServerConnection;
import org.agetac.common.api.InterventionConnection;
import org.agetac.common.model.impl.Intervention;

public class InterventionModel extends AbstractTableModel {

	private List<Intervention> interventions;
	private final String[] entetes = { "ID", "Lieu", "Moyens", "Cibles",
			"Sources", "Actions", "Messages", "Impliques" };
	
	private List<InterventionConnection> interventionsCon;
	
	public ServerConnection serv = new ServerConnection("localhost", "8112", "agetacserver");

	public InterventionModel() {
		super();
		interventions = new ArrayList<Intervention>();
		interventionsCon = new ArrayList<InterventionConnection>();
		getServInterAndInterCon();
	}
	
	private void getServInterAndInterCon(){
		
		interventions = Interventions.getInstance().getInterventions(); //interdit, coté serv..
		
		int nbInter = interventions.size();
		for (int i=0;i<nbInter;i++){
			interventionsCon.add(i, new InterventionConnection(interventions.get(i).getUniqueID(), serv));
			
		}
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
			return interventions.get(rowIndex).getVehicules().size();
		case 3:
			return interventions.get(rowIndex).getCibles().size();
		case 4:
			return interventions.get(rowIndex).getSources().size();
		case 5:
			return interventions.get(rowIndex).getActions().size();
		case 6:
			return interventions.get(rowIndex).getMessages().size();
		case 7:
			return interventions.get(rowIndex).getImpliques().size();

		default:
			return null; // Ne devrait jamais arriver
		}
	}

	public void addIntervention(Intervention inter,InterventionConnection interCon) {
		interventions.add(inter);
		interventionsCon.add(interCon);
		fireTableRowsInserted(interventions.size() - 1,
				interventions.size() - 1);
		System.out.println(interventions.toString());
		System.out.println(Interventions.getInstance().getInterventions());
	}

	public void removeIntervention(int rowIndex) {
		if (rowIndex != -1) {
			interventions.remove(rowIndex);
			interventionsCon.remove(rowIndex);

			fireTableRowsDeleted(rowIndex, rowIndex);
		}
	}
	
	public InterventionConnection getInterCon(int num){
		return interventionsCon.get(num);
	}
	
	public Intervention getInter (int num){
		return interventions.get(num);
	}

}
