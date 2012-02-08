package org.agetac.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import org.agetac.client.InterventionConnection;
import org.agetac.model.impl.Message;
import org.agetac.observer.Subject;
import org.agetac.server.db.Messages;

public class MessageModel extends AbstractTableModel implements Observer{
	
	private List<Message> messages;
	private final String[] entetes = { "ID", "Groupe Horaire", "Message" };

	public MessageModel() {
		super();
		messages = new ArrayList<Message>();
		addMessage(new Message("nouveau message","",""));
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
		//Messages.getInstance().addMessage(msg);
		fireTableRowsInserted(messages.size() - 1, messages.size() - 1);
	}

	public void removeMessage(int rowIndex) {
		messages.remove(rowIndex);

		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	
	public void update(Subject s) {
		System.out.println("MessageTableModel.update");
		messages = Messages.getInstance().getMessages();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (rowIndex == 0 && columnIndex >= 0 && columnIndex <3) return true;
		return false;
	}

}
