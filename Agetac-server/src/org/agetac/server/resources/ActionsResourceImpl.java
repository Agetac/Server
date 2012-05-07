package org.agetac.server.resources;

import java.util.Collection;

import org.agetac.common.dto.ActionDTO;
import org.agetac.common.resources.ActionsResource;
import org.agetac.server.dao.DAOFactory;
import org.agetac.server.dao.InterventionDAO;
import org.restlet.resource.ServerResource;


public class ActionsResourceImpl extends ServerResource implements ActionsResource {

	@Override
	public Collection<ActionDTO> retrieve() {
		InterventionDAO dao = DAOFactory.getDAOFactory().getInterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get("interId"));
		return dao.retrieveActions(interId);
	}

}
