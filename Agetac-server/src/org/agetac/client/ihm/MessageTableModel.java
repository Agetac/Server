package org.agetac.client.ihm;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.agetac.common.Message;
import org.agetac.observer.Observer;
import org.agetac.observer.Subject;
import org.agetac.server.db.Messages;

public class MessageTableModel extends AbstractTableModel implements Observer{

	private List<Message> messages;
	private final String[] entetes = { "ID", "Groupe Horaire", "Message" };

	public MessageTableModel() {
		super();
		messages = Messages.getInstance().getMessages();
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

	@Override
	public void update(Subject s) {
		System.out.println("MessageTableModel.update");
		messages = Messages.getInstance().getMessages();
	}

}
