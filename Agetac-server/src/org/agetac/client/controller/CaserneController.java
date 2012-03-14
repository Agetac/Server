package org.agetac.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.agetac.client.model.CaserneModel;
import org.agetac.client.view.CaserneView;
import org.agetac.common.model.impl.Caserne;
import org.agetac.common.model.impl.Message;

public class CaserneController implements ActionListener {

	private CaserneView view;
	private CaserneModel model;

	/**
	 * Constructeur de CaserneController
	 */
	public CaserneController(CaserneView view, CaserneModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Ajouter une caserne")) {
			if (!(view.getID().equals("ID")) && !(view.getNom().equals("Nom")) && !(view.getMoyens().equals("Moyens"))){
			model.addCaserne(new Caserne(view.getID(), view.getNom(), null));
			view.resetTxtFields();
			}
		}
		else if (e.getActionCommand().equals("Supprimer une caserne")) {
			model.removeCaserne(view.getSelectedLine());
		}
	}

}
