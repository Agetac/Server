package org.agetac.server.resources;

import org.agetac.common.dto.VehicleDTO;
import org.agetac.common.resources.VehicleResource;
import org.agetac.server.dao.DAOFactory;
import org.agetac.server.dao.InterventionDAO;
import org.restlet.resource.ServerResource;

public class VehicleResourceImpl extends ServerResource implements
		VehicleResource {

	@Override
	public VehicleDTO add(VehicleDTO vehicle) {
		InterventionDAO dao = DAOFactory.getDAOFactory().getInterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		vehicle = dao.addVehicle(interId, vehicle);

		return vehicle;
	}

	@Override
	public void update(VehicleDTO vehicle) {
		DAOFactory.getDAOFactory().getVehicleDAO().update(vehicle);
	}

	@Override
	public void remove() {
		long sourceId = Long.parseLong((String) getRequestAttributes().get(
				"vehicleId"));

		DAOFactory.getDAOFactory().getVehicleDAO().delete(sourceId);

	}

}
