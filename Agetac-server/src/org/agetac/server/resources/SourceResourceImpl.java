package org.agetac.server.resources;

import org.agetac.common.dto.SourceDTO;
import org.agetac.common.resources.SourceResource;
import org.agetac.server.db.InterventionDAOImpl;
import org.agetac.server.db.SourceDAOImpl;
import org.restlet.resource.ServerResource;

public class SourceResourceImpl extends ServerResource implements
		SourceResource {

	@Override
	public SourceDTO add(SourceDTO source) {
		InterventionDAOImpl dao = new InterventionDAOImpl();

		long interId = Long.parseLong((String) getRequestAttributes().get("interId"));
		source = dao.addSource(interId, source);
		
		return source;
	}

	@Override
	public void update(SourceDTO source) {
		SourceDAOImpl.getInstance().update(source);
	}

	@Override
	public void remove() {
		long sourceId = Long.parseLong((String) getRequestAttributes().get("sourceId"));

		SourceDAOImpl.getInstance().delete(sourceId);

	}

}
