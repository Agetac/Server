package org.agetac.server.resources;

import org.agetac.common.dto.VehicleDTO;
import org.agetac.common.resources.VehicleResource;
import org.agetac.server.db.InterventionDAO;
import org.agetac.server.db.InterventionDAOImpl;
import org.agetac.server.db.SourceDAOImpl;
import org.agetac.server.db.VehicleDAOImpl;
import org.restlet.resource.ServerResource;

public class VehicleResourceImpl extends ServerResource implements VehicleResource {

	@Override
	public VehicleDTO add(VehicleDTO vehicle) {
		InterventionDAO dao = new InterventionDAOImpl();

		long interId = Long.parseLong((String) getRequestAttributes().get("interId"));
		vehicle = dao.addVehicle(interId, vehicle);
		
		return vehicle;
	}

	@Override
	public void update(VehicleDTO vehicle) {
		VehicleDAOImpl.getInstance().update(vehicle);
	}

	@Override
	public void remove() {
		long sourceId = Long.parseLong((String) getRequestAttributes().get("vehicleId"));

		SourceDAOImpl.getInstance().delete(sourceId);

	}

}
