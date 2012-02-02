package org.agetac.client.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.agetac.client.model.VehiculeModel;
import org.agetac.client.view.VehiculeView;

public class VehiculeControler implements ActionListener {

	private VehiculeView view;
	private VehiculeModel model;

	/**
	 * Constructeur de VehiculeControler
	 */
	public VehiculeControler(VehiculeView view, VehiculeModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
