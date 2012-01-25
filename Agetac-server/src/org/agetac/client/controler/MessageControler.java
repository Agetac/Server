package org.agetac.client.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.agetac.client.model.MessageModel;
import org.agetac.client.view.MessageView;

public class MessageControler implements ActionListener {

	private MessageView view;
	private MessageModel model;

	/**
	 * Constructeur de MessageControler
	 */
	public MessageControler(MessageView view, MessageModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
