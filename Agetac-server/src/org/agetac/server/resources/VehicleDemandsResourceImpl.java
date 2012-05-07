package org.agetac.server.resources;

import java.util.Collection;

import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.common.resources.VehicleDemandsResource;
import org.agetac.server.db.InterventionDAO;
import org.agetac.server.db.InterventionDAOImpl;
import org.restlet.resource.ServerResource;


public class VehicleDemandsResourceImpl extends ServerResource implements
		VehicleDemandsResource {

	@Override
	public Collection<VehicleDemandDTO> retrieve() {
		InterventionDAO dao = new InterventionDAOImpl();

		long interId = Long.parseLong((String) getRequestAttributes().get("interId"));
		return dao.retrieveVehicleDemands(interId);

	}

}
