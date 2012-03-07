package org.agetac.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import org.agetac.client.InterventionConnection;
import org.agetac.client.exception.BadResponseException;
import org.agetac.model.impl.Message;
import org.agetac.observer.Subject;
import org.json.JSONException;

public class MessageModel extends AbstractTableModel implements Observer{
	
	private List<Message> messages;
	private final String[] entetes = { "ID", "Groupe Horaire", "Message" };
	private InterventionConnection interCon;

	public MessageModel(InterventionConnection i) {
		super();
		interCon = i;
		try {
			messages = interCon.getMessages();
		} catch (BadResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getRowCount() {
		return messages.size();
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
			return messages.get(rowIndex).getUniqueID();
		case 1:
			return messages.get(rowIndex).getDate();
		case 2:
			return messages.get(rowIndex).getMessage();

		default:
			return null; // Ne devrait jamais arriver
		}
	}

	public void addMessage(Message msg) {
		messages.add(msg);
		try {
			interCon.putMessage(msg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fireTableRowsInserted(messages.size() - 1, messages.size() - 1);
	}

	public void removeMessage(int rowIndex) {
		if (rowIndex != -1){
			try {
				interCon.deleteMessage(messages.get(rowIndex));
				messages.remove(rowIndex);
				fireTableRowsDeleted(rowIndex, rowIndex);
			} catch (BadResponseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}

	
	public void update(Subject s) {
		System.out.println("MessageTableModel.update");
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	

}
