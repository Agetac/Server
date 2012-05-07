package org.agetac.server.resources;

import java.util.Collection;

import org.agetac.common.dto.TargetDTO;
import org.agetac.common.resources.TargetsResource;
import org.agetac.server.db.InterventionDAO;
import org.agetac.server.db.InterventionDAOImpl;
import org.restlet.resource.ServerResource;


public class TargetsResourceImpl extends ServerResource implements
		TargetsResource {

	@Override
	public Collection<TargetDTO> retrieve() {
		InterventionDAO dao = new InterventionDAOImpl();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		return dao.retrieveTargets(interId);
	}

}
