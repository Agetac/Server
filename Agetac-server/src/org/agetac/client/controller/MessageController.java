package org.agetac.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import org.agetac.client.model.MessageModel;
import org.agetac.client.view.MessageView;
import org.agetac.common.dto.MessageDTO;

public class MessageController implements ActionListener {

	private MessageView view;
	private MessageModel model;

	/**
	 * MessageController constructor
	 */
	public MessageController(MessageView view, MessageModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Add message")) {
			if (!(view.getMessage().equals("Message"))){
			model.addMessage(new MessageDTO(view.getMessage(), new Date()));
			view.resetTxtFields();
			}
		}
	}
}
