package org.agetac.server.resources;

import java.util.Collection;

import org.agetac.common.dto.VehicleDTO;
import org.agetac.common.resources.VehiclesResource;
import org.agetac.server.db.InterventionDAO;
import org.restlet.resource.ServerResource;


public class VehiclesResourceImpl extends ServerResource implements
		VehiclesResource {

	@Override
	public Collection<VehicleDTO> retrieve() {
		InterventionDAO dao = new InterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		return dao.retrieveVehicles(interId);

	}

}
