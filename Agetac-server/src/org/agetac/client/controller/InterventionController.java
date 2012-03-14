package org.agetac.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.agetac.client.model.InterventionModel;
import org.agetac.client.view.InterventionView;
import org.agetac.common.model.impl.Intervention;
import org.agetac.common.model.impl.Message;

public class InterventionController implements ActionListener {

	private InterventionView view;
	private InterventionModel model;

	/**
	 * Constructeur de InterventionController
	 */
	public InterventionController(InterventionView view, InterventionModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Ajouter un message")) {
		//	if (!(view.getID().equals("ID")) && !(view.getLieu().equals("Lieu")) && !(view.getMoyens().equals("Moyens")) && !(view.getCibles().equals("Cibles")) && !(view.getSources().equals("Sources")) && !(view.getActions().equals("Actions")) && !(view.getMessages().equals("Messages")) && !(view.getImpliques().equals("Impliques"))){
			model.addIntervention(new Intervention(view.getID()));
			view.resetTxtFields();
			//}
		}
		else if (e.getActionCommand().equals("Supprimer un message")) {
			model.removeIntervention(view.getSelectedLine());
		}
	}

}
