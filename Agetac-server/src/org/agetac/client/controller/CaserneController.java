package org.agetac.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.agetac.client.model.CaserneModel;
import org.agetac.client.view.CaserneView;

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
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
