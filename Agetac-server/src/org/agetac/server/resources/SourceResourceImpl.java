package org.agetac.server.resources;

import org.agetac.common.dto.SourceDTO;
import org.agetac.common.resources.SourceResource;
import org.agetac.server.db.InterventionDAO;
import org.agetac.server.db.SourceDAO;
import org.restlet.resource.ServerResource;

public class SourceResourceImpl extends ServerResource implements
		SourceResource {

	@Override
	public SourceDTO add(SourceDTO source) {
		InterventionDAO dao = new InterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		dao.addSource(interId, source);
		
		return source;
	}

	@Override
	public void update(SourceDTO source) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove() {
		long sourceId = Long.parseLong((String) getRequestAttributes().get(
				"sourceId"));

		SourceDAO.getInstance().delete(sourceId);

	}

}
