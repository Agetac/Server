package org.agetac.client.model;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import org.agetac.common.api.InterventionConnection;
import org.agetac.common.exception.BadResponseException;
import org.agetac.common.model.impl.Source;
import org.json.JSONException;

public class SourceModel extends AbstractTableModel implements Observer {

	private List<Source> sources;
	private final String[] entetes = { "ID", "Position","Type" };
	private InterventionConnection interCon;

	public SourceModel(InterventionConnection i) {
		super();
		interCon = i;
		try {
			sources = interCon.getSources();
		} catch (BadResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		case 2:
			return sources.get(rowIndex).getType();
		default:
			return null; // Ne devrait jamais arriver
		}
	}

	public void addSource(Source src) {

		try {
			interCon.putSource(src);
			sources.add(src);
			fireTableRowsInserted(sources.size() - 1, sources.size() - 1);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void removeSource(int rowIndex) {
		if (rowIndex != -1) {
			try {
				interCon.deleteSource(sources.get(rowIndex));
				sources.remove(rowIndex);
				fireTableRowsDeleted(rowIndex, rowIndex);
			} catch (BadResponseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
