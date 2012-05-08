package org.agetac.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.agetac.client.model.VehicleDemandModel;
import org.agetac.client.view.VehicleDemandView;
import org.agetac.common.dto.VehicleDTO;
import org.agetac.common.dto.VehicleDTO.VehicleState;
import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.common.dto.VehicleDemandDTO.DemandState;

public class VehicleDemandController implements ActionListener {

	private VehicleDemandView view;
	private VehicleDemandModel model;
	private VehicleDemandDTO dem;

	/**
	 * VehicleDemandController constructor
	 */
	public VehicleDemandController(VehicleDemandView view,
			VehicleDemandModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Accepter") && (view.getSelectedLine()>-1)) {
			dem = model.getVehicleDemand(view.getSelectedLine());
			if(dem.getState()==DemandState.ASKED){
				dem.setState(DemandState.ACCEPTED);
				model.updateVehicleDemand(dem);
			}
		} else if (e.getActionCommand().equals("Refuser") && (view.getSelectedLine()>-1)) {
			dem = model.getVehicleDemand(view.getSelectedLine());
			dem.setState(DemandState.REFUSED);
			model.updateVehicleDemand(dem);
		}

	}

}
