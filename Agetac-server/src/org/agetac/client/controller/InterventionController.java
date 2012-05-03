package org.agetac.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.agetac.client.model.AgentModel;
import org.agetac.client.model.BarrackModel;
import org.agetac.client.model.InterventionModel;
import org.agetac.client.model.MessageModel;
import org.agetac.client.model.SourceModel;
import org.agetac.client.model.VehicleModel;
import org.agetac.client.view.AgentView;
import org.agetac.client.view.BarrackView;
import org.agetac.client.view.InterventionView;
import org.agetac.client.view.MessageView;
import org.agetac.client.view.SourceView;
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
		if (e.getActionCommand().equals("Add intervention")) {

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
				AgentModel agentModel = new AgentModel(client, interId);
				AgentView agentView = new AgentView(agentModel);
				VehicleModel vehicleModel = new VehicleModel(client, interId);
				VehicleView vehicleView = new VehicleView(vehicleModel);
				BarrackModel casModel = new BarrackModel(client, interId);
				BarrackView casView = new BarrackView(casModel);
				SourceModel srcModel = new SourceModel(client, interId);
				SourceView srcView = new SourceView(srcModel);
			}
		}
	}

}
