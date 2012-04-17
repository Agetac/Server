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
		dao.addAction(interId, action);
		
		return action;
	}

	@Override
	public void update(ActionDTO source) {
		// TODO Auto-generated method stub
	}

	@Override
	public void remove() {
		long sourceId = Long.parseLong((String) getRequestAttributes().get("actionId"));

		ActionDAO.getInstance().delete(sourceId);

	}

}
