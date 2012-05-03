package org.agetac.client.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.agetac.common.client.AgetacClient;
import org.agetac.common.dto.MessageDTO;

@SuppressWarnings("serial")
public class MessageModel extends AbstractTableModel{

	private List<MessageDTO> messages;
	private final String[] headers = { "ID", "Date", "Message" };
	private AgetacClient client;
	private long interID;

	public MessageModel(AgetacClient c, long interId) {
		client = c;
		interID = interId;
		messages = (List<MessageDTO>) client.getMessages(interId);
		// may crash because of the cast...
	}

	public int getRowCount() {
		return messages.size();
	}

	public int getColumnCount() {
		return headers.length;
	}

	public String getColumnName(int columnIndex) {
		return headers[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return messages.get(rowIndex).getId();
		case 1:
			return messages.get(rowIndex).getDate();
		case 2:
			return messages.get(rowIndex).getText();

		default:
			return null; // Should never happen
		}
	}

	public void addMessage(MessageDTO msg) {
		client.addMessage(interID, msg);
		messages.add(msg);
		fireTableRowsInserted(messages.size() - 1, messages.size() - 1);
	}

}
