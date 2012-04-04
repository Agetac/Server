package org.agetac.server.resources;

import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.common.resources.VehicleDemandResource;
import org.agetac.server.db.InterventionDAO;
import org.restlet.resource.ServerResource;


public class VehicleDemandResourceImpl extends ServerResource implements
		VehicleDemandResource {

	@Override
	public VehicleDemandDTO add(VehicleDemandDTO vehicleDemand) {

		InterventionDAO dao = new InterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		dao.addVehicleDemand(interId, vehicleDemand);

		return vehicleDemand;
	}
}
