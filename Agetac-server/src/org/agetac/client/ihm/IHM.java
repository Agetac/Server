package org.agetac.client.ihm;

import org.agetac.client.model.AgentModel;

public class IHM {

	private static MessageList messageList;
	private static AgentList agentList;

	/**
	 * @param args
	 */
	public IHM() {
		
		MessageTableModel messageModel = new MessageTableModel();
		messageList = new MessageList(messageModel);
		messageList.setVisible(true);
		AgentModel agentModel = new AgentModel();
		agentList = new AgentList(agentModel);
		agentList.setVisible(true);
	}
	
	
	
}
