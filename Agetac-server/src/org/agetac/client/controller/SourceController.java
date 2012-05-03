package org.agetac.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.agetac.client.model.SourceModel;
import org.agetac.client.view.SourceView;
import org.agetac.common.dto.SourceDTO;

public class SourceController implements ActionListener {

	private SourceView view;
	private SourceModel model;

	/**
	 * Constructeur de SourceController
	 */
	public SourceController(SourceView view, SourceModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Ajouter une source")) {
			if (!(view.getID().equals("ID"))
					&& !(view.getLatitude().equals("Latitude"))
					&& !(view.getLongitude().equals("Longitude"))) {
				if (view.getSourceType()!=null){
				model.addSource(new SourceDTO(view.getSourceType()));
				view.resetTxtFields();
				}
			}
		} else if (e.getActionCommand().equals("Supprimer une source")) {
			model.removeSource(view.getSelectedLine());
		}
	}
}
