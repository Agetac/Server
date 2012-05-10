package org.agetac.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import org.agetac.client.model.VehicleModel;
import org.agetac.client.view.VehicleView;
import org.agetac.common.dto.GroupDTO;
import org.agetac.common.dto.PositionDTO;
import org.agetac.common.dto.VehicleDTO;
import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.common.dto.VehicleDTO.VehicleState;
import org.agetac.common.dto.VehicleDTO.VehicleType;
import org.agetac.common.dto.VehicleDemandDTO.DemandState;

public class VehicleController implements ActionListener {

	private VehicleView view;
	private VehicleModel model;
	private VehicleDTO v;
	/**
	 * VehicleController constructor
	 */
	public VehicleController(VehicleView view, VehicleModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Ajouter")) {

			VehicleDTO v = new VehicleDTO(view.getName(), VehicleState.ALERTE,	VehicleType.valueOf(view.getVehType()), new PositionDTO(), null);
			v.setDemandTime(new Date());
			model.addVehicle(v);
			view.resetTxtFields();

		} else if (e.getActionCommand().equals("Faire rentrer")) {
			if(view.getSelectedLine()>-1){
				v = model.getVehicle(view.getSelectedLine());
				v.setState(VehicleState.DEMOBILISE);
				v.setRetTime(new Date());
				model.updateVehicle(view.getSelectedLine(),v);
			}
		}
		

	}
}
