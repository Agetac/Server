package org.agetac.server.resources;

import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.common.resources.VehicleDemandResource;
import org.agetac.server.db.InterventionDAOImpl;
import org.agetac.server.db.VehicleDemandDAOImpl;
import org.restlet.resource.ServerResource;

public class VehicleDemandResourceImpl extends ServerResource implements
		VehicleDemandResource {

	@Override
	public VehicleDemandDTO add(VehicleDemandDTO vehicleDemand) {
		InterventionDAOImpl dao = new InterventionDAOImpl();

		long interId = Long.parseLong((String) getRequestAttributes().get("interId"));
		vehicleDemand = dao.addVehicleDemand(interId, vehicleDemand);

		return vehicleDemand;
	}

	@Override
	public void update(VehicleDemandDTO demand) {
		VehicleDemandDAOImpl.getInstance().update(demand);
	}

	@Override
	public void remove() {
		long demandId = Long.parseLong((String) getRequestAttributes().get("vdId"));

		VehicleDemandDAOImpl.getInstance().delete(demandId);
	}
}
