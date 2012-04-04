package org.agetac.server.resources;

import java.util.Collection;

import org.agetac.common.dto.ActionDTO;
import org.agetac.common.dto.SourceDTO;
import org.agetac.common.resources.ActionsResource;
import org.agetac.common.resources.SourcesResource;
import org.agetac.server.db.InterventionDAO;
import org.restlet.resource.ServerResource;


public class ActionsResourceImpl extends ServerResource implements ActionsResource {

	@Override
	public Collection<ActionDTO> retrieve() {
		InterventionDAO dao = new InterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get("interId"));
		return dao.retrieveActions(interId);
	}

}
