package org.agetac.server.resources;

import org.agetac.common.dto.VehicleDTO;
import org.agetac.common.resources.VehicleResource;
import org.agetac.server.db.InterventionDAO;
import org.agetac.server.db.SourceDAO;
import org.restlet.resource.ServerResource;

public class VehicleResourceImpl extends ServerResource implements VehicleResource {

	@Override
	public VehicleDTO add(VehicleDTO vehicle) {
		InterventionDAO dao = new InterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get("interId"));
		dao.addVehicle(interId, vehicle);
		
		return vehicle;
	}

	@Override
	public void update(VehicleDTO source) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove() {
		long sourceId = Long.parseLong((String) getRequestAttributes().get("vehicleId"));

		SourceDAO.getInstance().delete(sourceId);

	}

}
