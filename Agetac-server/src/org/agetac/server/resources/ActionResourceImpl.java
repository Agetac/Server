package org.agetac.server.resources;

import org.agetac.common.dto.ActionDTO;
import org.agetac.common.resources.ActionResource;
import org.agetac.server.db.InterventionDAO;
import org.agetac.server.db.ActionDAO;
import org.restlet.resource.ServerResource;

public class ActionResourceImpl extends ServerResource implements
		ActionResource {

	@Override
	public ActionDTO add(ActionDTO action) {
		InterventionDAO dao = new InterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get("interId"));
		action = dao.addAction(interId, action);
		
		return action;
	}

	@Override
	public void update(ActionDTO action) {
		ActionDAO.getInstance().update(action);
	}

	@Override
	public void remove() {
		long sourceId = Long.parseLong((String) getRequestAttributes().get("actionId"));

		ActionDAO.getInstance().delete(sourceId);

	}

}
