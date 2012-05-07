package org.agetac.server.resources;

import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.common.resources.VehicleDemandResource;
import org.agetac.server.dao.DAOFactory;
import org.agetac.server.dao.InterventionDAO;
import org.restlet.resource.ServerResource;

public class VehicleDemandResourceImpl extends ServerResource implements
		VehicleDemandResource {

	@Override
	public VehicleDemandDTO add(VehicleDemandDTO vehicleDemand) {
		InterventionDAO dao = DAOFactory.getDAOFactory().getInterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		vehicleDemand = dao.addVehicleDemand(interId, vehicleDemand);

		return vehicleDemand;
	}

	@Override
	public void update(VehicleDemandDTO demand) {
		DAOFactory.getDAOFactory().getVehicleDemandDAO().update(demand);
	}

	@Override
	public void remove() {
		long demandId = Long.parseLong((String) getRequestAttributes().get(
				"vdId"));

		DAOFactory.getDAOFactory().getVehicleDemandDAO().delete(demandId);
	}
}
