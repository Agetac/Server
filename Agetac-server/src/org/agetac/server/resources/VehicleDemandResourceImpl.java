package org.agetac.server.resources;

import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.common.resources.VehicleDemandResource;
import org.agetac.server.db.InterventionDAO;
import org.agetac.server.db.VehicleDemandDAO;
import org.restlet.resource.ServerResource;

public class VehicleDemandResourceImpl extends ServerResource implements
		VehicleDemandResource {

	@Override
	public VehicleDemandDTO add(VehicleDemandDTO vehicleDemand) {
		InterventionDAO dao = new InterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get("interId"));
		vehicleDemand = dao.addVehicleDemand(interId, vehicleDemand);

		return vehicleDemand;
	}

	@Override
	public void update(VehicleDemandDTO demand) {
		VehicleDemandDAO.getInstance().update(demand);
	}

	@Override
	public void remove() {
		long demandId = Long.parseLong((String) getRequestAttributes().get("vdId"));

		VehicleDemandDAO.getInstance().delete(demandId);
	}
}
