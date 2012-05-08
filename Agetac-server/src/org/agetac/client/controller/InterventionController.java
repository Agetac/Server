package org.agetac.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

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
import org.agetac.common.dto.PositionDTO;

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

			PositionDTO position;
			try {
				position = view.getPosition();
			} catch (Exception ex) {
				view.showError(ex.getMessage());
				return;
			}

			AgetacClient client = model.getClient();

			InterventionDTO inter = client.createIntervention();

			inter.setPosition(position);

			inter.setName(view.getName());
			client.updateIntervention(inter);

			model.addIntervention(inter);
			view.resetTxtFields();

		} else if (e.getActionCommand().equals("Intervention details")) {

			if (view.getSelectedLine() != -1) {
				AgetacClient client = model.getClient();
				long interId = model.getInter(view.getSelectedLine()).getId();

				final MessageModel msgModel = new MessageModel(client, interId);
				MessageView msgView = new MessageView(msgModel);

				final VehicleModel vehicleModel = new VehicleModel(client,
						interId);
				VehicleView vehicleView = new VehicleView(vehicleModel);
				final VehicleDemandModel casModel = new VehicleDemandModel(
						client, interId);
				VehicleDemandView casView = new VehicleDemandView(casModel);

				Timer timer = new Timer();
				timer.schedule(new TimerTask() {

					@Override
					public void run() {
						msgModel.update();
						vehicleModel.update();
						casModel.update();

					}
				}, 0, 5 * 1000);
			}

		}
	}

}
