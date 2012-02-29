package org.agetac.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.agetac.client.model.VehiculeModel;
import org.agetac.client.view.VehiculeView;
import org.agetac.model.impl.Groupe;
import org.agetac.model.impl.Message;
import org.agetac.model.impl.Position;
import org.agetac.model.impl.Vehicule;
import org.agetac.model.impl.Vehicule.EtatVehicule;

public class VehiculeController implements ActionListener {

	private VehiculeView view;
	private VehiculeModel model;

	/**
	 * Constructeur de VehiculeController
	 */
	public VehiculeController(VehiculeView view, VehiculeModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Ajouter un vehicule")) {
			if (!(view.getID().equals("ID")) && !(view.getNom().equals("Nom")) && !(view.getPosition().equals("Position")) && !(view.getCaserne().equals("Caserne")) && !(view.getEtat().equals("Etat")) && !(view.getGroupe().equals("Groupe"))){
			model.addVehicule(new Vehicule(view.getID(), view.getNom(), new Position(0, 0), view.getCaserne(), null, new Groupe(null, null, null)));
			view.resetTxtFields();
			}
		}
		else if (e.getActionCommand().equals("Supprimer un vehicule")) {
			model.removeVehicule(view.getSelectedLine());
		}

	}

}
