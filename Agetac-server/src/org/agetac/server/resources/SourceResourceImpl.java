package org.agetac.server.resources;

import org.agetac.common.dto.SourceDTO;
import org.agetac.common.resources.SourceResource;
import org.agetac.server.dao.DAOFactory;
import org.agetac.server.dao.InterventionDAO;
import org.restlet.resource.ServerResource;

public class SourceResourceImpl extends ServerResource implements
		SourceResource {

	@Override
	public SourceDTO add(SourceDTO source) {

		InterventionDAO dao = DAOFactory.getDAOFactory().getInterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		source = dao.addSource(interId, source);

		return source;
	}

	@Override
	public void update(SourceDTO source) {
		DAOFactory.getDAOFactory().getSourceDAO().update(source);
	}

	@Override
	public void remove() {
		long sourceId = Long.parseLong((String) getRequestAttributes().get(
				"sourceId"));

		DAOFactory.getDAOFactory().getSourceDAO().delete(sourceId);

	}

}
