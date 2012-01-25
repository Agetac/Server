package org.agetac.client.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.agetac.client.model.AgentModel;
import org.agetac.client.view.AgentView;

public class AgentControler implements ActionListener {

	private AgentView view;
	private AgentModel model;

	/**
	 * Constructeur de AgentControler
	 */
	public AgentControler(AgentView view, AgentModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
