package org.agetac.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.agetac.client.model.VehicleDemandModel;
import org.agetac.client.model.InterventionModel;
import org.agetac.client.model.MessageModel;
import org.agetac.client.model.VehicleModel;
import org.agetac.client.view.VehicleDemandView;
import org.agetac.client.view.InterventionView;
import org.agetac.client.view.MessageView;
import org.agetac.client.view.VehicleView;
import org.agetac.common.client.AgetacClient;
import org.agetac.common.dto.InterventionDTO;

public class InterventionController implements ActionListener {

	private InterventionView view;
	private InterventionModel model;

	/**
	 * InterventionController constructor
	 */
	public InterventionController(InterventionView view, InterventionModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Ajouter intervention")) {

			InterventionDTO inter = new InterventionDTO();
			inter.setPosition(view.getPosition());
			inter.setName(view.getName());
			model.addIntervention(inter);
			view.resetTxtFields();
		} else if (e.getActionCommand().equals("Intervention details")) {
			if (view.getSelectedLine() != -1) {
				AgetacClient client = model.getClient();
				long interId = model.getInter(view.getSelectedLine()).getId();

				MessageModel msgModel = new MessageModel(client, interId);
				MessageView msgView = new MessageView(msgModel);
				VehicleModel vehicleModel = new VehicleModel(client, interId);
				VehicleView vehicleView = new VehicleView(vehicleModel);
				VehicleDemandModel casModel = new VehicleDemandModel(client, interId);
				VehicleDemandView casView = new VehicleDemandView(casModel);
			}
		}
	}

}
