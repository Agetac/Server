package org.agetac.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import org.agetac.client.InterventionConnection;
import org.agetac.model.impl.Source;
import org.agetac.observer.Subject;
import org.agetac.server.db.Sources;
import org.json.JSONException;

public class SourceModel extends AbstractTableModel implements Observer{
	
	private List<Source> sources;
	private final String[] entetes = { "ID", "Position"};
	private InterventionConnection interCon;

	public SourceModel(InterventionConnection i) {
		super();
		interCon = i;
		sources = interCon.getSources();
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
			return sources.get(rowIndex).getUniqueID();
		case 1:
			return sources.get(rowIndex).getPosition();
		default:
			return null; // Ne devrait jamais arriver
		}
	}

	public void addSource(Source src) {
		sources.add(src);
		try {
			interCon.putSource(src);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fireTableRowsInserted(sources.size() - 1, sources.size() - 1);
	}

	public void removeSource(int rowIndex) {
		if (rowIndex != -1){
			interCon.deleteSource(sources.get(rowIndex));
			sources.remove(rowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
		}
	}

	
	public void update(Subject s) {
		System.out.println("MessageTableModel.update");
		sources = Sources.getInstance().getSources();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	

}
