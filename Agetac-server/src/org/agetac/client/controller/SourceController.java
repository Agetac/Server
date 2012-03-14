package org.agetac.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.agetac.client.model.SourceModel;
import org.agetac.client.view.SourceView;
import org.agetac.common.model.impl.Position;
import org.agetac.common.model.impl.Source;

public class SourceController implements ActionListener {

	private SourceView view;
	private SourceModel model;

	/**
	 * Constructeur de MessageController
	 */
	public SourceController(SourceView view, SourceModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Ajouter une source")) {
			if (!(view.getID().equals("ID")) && !(view.getLatitude().equals("Latitude")) && !(view.getLongitude().equals("Longitude"))){
			model.addSource(new Source(view.getID(), new Position(Double.parseDouble(view.getLatitude()), Double.parseDouble(view.getLongitude()))));
			view.resetTxtFields();
			}
		}
		else if (e.getActionCommand().equals("Supprimer une source")) {
			model.removeSource(view.getSelectedLine());
		}
	}
}
