package org.agetac.server.resources;

import org.agetac.common.dto.VehicleDTO;
import org.agetac.common.resources.VehicleResource;
import org.agetac.server.db.InterventionDAO;
import org.agetac.server.db.SourceDAO;
import org.agetac.server.db.VehicleDAO;
import org.restlet.resource.ServerResource;

public class VehicleResourceImpl extends ServerResource implements VehicleResource {

	@Override
	public VehicleDTO add(VehicleDTO vehicle) {
		InterventionDAO dao = new InterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get("interId"));
		vehicle = dao.addVehicle(interId, vehicle);
		
		return vehicle;
	}

	@Override
	public void update(VehicleDTO vehicle) {
		VehicleDAO.getInstance().update(vehicle);
	}

	@Override
	public void remove() {
		long sourceId = Long.parseLong((String) getRequestAttributes().get("vehicleId"));

		SourceDAO.getInstance().delete(sourceId);

	}

}
