package org.agetac.server.client.ihm;

public class IHM {

	private static MessageList messageList;

	/**
	 * @param args
	 */
	public IHM() {
		
		MessageTableModel messageModel = new MessageTableModel();
		messageList = new MessageList(messageModel);
		messageList.setVisible(true);
	}
	
	
	
}
