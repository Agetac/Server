package org.agetac.client.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.agetac.client.model.CaserneModel;
import org.agetac.client.view.CaserneView;

public class CaserneControler implements ActionListener {

	private CaserneView view;
	private CaserneModel model;

	/**
	 * Constructeur de CaserneControler
	 */
	public CaserneControler(CaserneView view, CaserneModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
