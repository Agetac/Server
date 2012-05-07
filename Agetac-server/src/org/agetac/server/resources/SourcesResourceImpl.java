package org.agetac.server.resources;

import java.util.Collection;

import org.agetac.common.dto.SourceDTO;
import org.agetac.common.resources.SourcesResource;
import org.agetac.server.db.InterventionDAO;
import org.agetac.server.db.InterventionDAOImpl;
import org.restlet.resource.ServerResource;

public class SourcesResourceImpl extends ServerResource implements
		SourcesResource {

	@Override
	public Collection<SourceDTO> retrieve() {
		InterventionDAO dao = new InterventionDAOImpl();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));

		return dao.retrieveSources(interId);
	}

}
