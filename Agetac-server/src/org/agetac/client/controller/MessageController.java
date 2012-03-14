package org.agetac.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.agetac.client.model.MessageModel;
import org.agetac.client.view.MessageView;
import org.agetac.common.model.impl.Message;

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
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Ajouter un message")) {
			if (!(view.getID().equals("ID")) && !(view.getMessage().equals("Message")) && !(view.getGH().equals("Groupe Horaire"))){
			model.addMessage(new Message(view.getID(), view.getMessage(), view.getGH()));
			view.resetTxtFields();
			}
		}
		else if (e.getActionCommand().equals("Supprimer un message")) {
			model.removeMessage(view.getSelectedLine());
		}
	}
}
