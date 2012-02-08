package org.agetac.client.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import org.agetac.client.model.MessageModel;
import org.agetac.client.view.MessageView;
import org.agetac.model.impl.Message;

public class MessageControler implements ActionListener {

	private MessageView view;
	private MessageModel model;
	private Random r;

	/**
	 * Constructeur de MessageControler
	 */
	public MessageControler(MessageView view, MessageModel model) {
		this.view = view;
		this.model = model;
		this.r = new Random();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Ajouter un message")){
			model.addMessage(new Message(String.valueOf(r.nextInt(Integer.MAX_VALUE)),"bob","groupe"));//view.openMenAjouter();
		}
	}
	

}
