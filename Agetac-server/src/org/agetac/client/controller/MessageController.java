package org.agetac.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.agetac.client.model.MessageModel;
import org.agetac.client.view.MessageView;
import org.agetac.model.impl.Message;

public class MessageController implements ActionListener {

	private MessageView view;
	private MessageModel model;

	/**
	 * Constructeur de MessageController
	 */
	public MessageController(MessageView view, MessageModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//exemple
		model.addMessage(new Message("12", "Mon message", "12345"));

	}

}
