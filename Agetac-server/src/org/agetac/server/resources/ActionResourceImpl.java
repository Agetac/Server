package org.agetac.server.resources;

import org.agetac.common.dto.ActionDTO;
import org.agetac.common.resources.ActionResource;
import org.agetac.server.db.InterventionDAO;
import org.agetac.server.db.InterventionDAOImpl;
import org.agetac.server.db.ActionDAOImpl;
import org.restlet.resource.ServerResource;

public class ActionResourceImpl extends ServerResource implements
		ActionResource {

	@Override
	public ActionDTO add(ActionDTO action) {
		InterventionDAO dao = new InterventionDAOImpl();

		long interId = Long.parseLong((String) getRequestAttributes().get("interId"));
		action = dao.addAction(interId, action);
		
		return action;
	}

	@Override
	public void update(ActionDTO action) {
		ActionDAOImpl.getInstance().update(action);
	}

	@Override
	public void remove() {
		long sourceId = Long.parseLong((String) getRequestAttributes().get("actionId"));

		ActionDAOImpl.getInstance().delete(sourceId);

	}

}
