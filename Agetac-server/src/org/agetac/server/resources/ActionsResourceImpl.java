package org.agetac.server.resources;

import java.util.Collection;

import org.agetac.common.dto.ActionDTO;
import org.agetac.common.resources.ActionsResource;
import org.agetac.server.db.InterventionDAOImpl;
import org.restlet.resource.ServerResource;


public class ActionsResourceImpl extends ServerResource implements ActionsResource {

	@Override
	public Collection<ActionDTO> retrieve() {
		InterventionDAOImpl dao = new InterventionDAOImpl();

		long interId = Long.parseLong((String) getRequestAttributes().get("interId"));
		return dao.retrieveActions(interId);
	}

}
