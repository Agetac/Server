package org.agetac.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.agetac.client.model.VehicleModel;
import org.agetac.client.view.VehicleView;
import org.agetac.common.dto.GroupDTO;
import org.agetac.common.dto.PositionDTO;
import org.agetac.common.dto.VehicleDTO;
import org.agetac.common.dto.VehicleDTO.VehicleState;
import org.agetac.common.dto.VehicleDTO.VehicleType;

public class VehicleController implements ActionListener {

	private VehicleView view;
	private VehicleModel model;

	/**
	 * Constructeur de VehiculeController
	 */
	public VehicleController(VehicleView view, VehicleModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Add vehicle")) {
			if (!(view.getName().equals("Name"))
					&& !(view.getPosition().equals("Position"))
					&& !(view.getBarrack().equals("Barrack"))
					&& !(view.getStates().equals("State"))
					&& !(view.getGroup().equals("Group"))) {
				// TODO experimental
				model.addVehicle(new VehicleDTO(new PositionDTO(0, 0), VehicleType.FPT, view.getBarrack(), VehicleState.ALERTE, new GroupDTO(), "0102"));
				view.resetTxtFields();
			}
		} else if (e.getActionCommand().equals("Delete vehicle")) {
			model.removeVehicle(view.getSelectedLine());
		}

	}

}
