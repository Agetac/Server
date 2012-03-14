package org.agetac.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.agetac.client.model.AgentModel;
import org.agetac.client.view.AgentView;
import org.agetac.common.model.impl.Agent;
import org.agetac.common.model.impl.Groupe;
import org.agetac.common.model.impl.Position;
import org.agetac.common.model.impl.Vehicule;

public class AgentController implements ActionListener {

	private AgentView view;
	private AgentModel model;

	/**
	 * Constructeur de AgentController
	 */
	public AgentController(AgentView view, AgentModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Ajouter un agent")) {
			if (!(view.getID().equals("ID")) && !(view.getNom().equals("Nom")) && !(view.getAptitudes().equals("Aptitudes")) && !(view.getSubordonnes().equals("Subordonnes"))){
			model.addAgent(new Agent(view.getID(), view.getNom(),null,null));
			view.resetTxtFields();
			}
		}
		else if (e.getActionCommand().equals("Supprimer un agent")) {
			model.removeAgent(view.getSelectedLine());
		}

	}

}
