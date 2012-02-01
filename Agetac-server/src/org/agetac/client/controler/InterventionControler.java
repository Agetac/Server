package org.agetac.client.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.agetac.client.model.InterventionModel;
import org.agetac.client.view.InterventionView;

public class InterventionControler implements ActionListener {

	private InterventionView view;
	private InterventionModel model;

	/**
	 * Constructeur de InterventionControler
	 */
	public InterventionControler(InterventionView view, InterventionModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
