package org.agetac.server.resources;

import org.agetac.common.dto.ActionDTO;
import org.agetac.common.resources.ActionResource;
import org.agetac.server.dao.DAOFactory;
import org.agetac.server.dao.InterventionDAO;
import org.restlet.resource.ServerResource;

public class ActionResourceImpl extends ServerResource implements
		ActionResource {

	@Override
	public ActionDTO add(ActionDTO action) {

		InterventionDAO dao = DAOFactory.getDAOFactory().getInterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		action = dao.addAction(interId, action);

		return action;
	}

	@Override
	public void update(ActionDTO action) {
		DAOFactory.getDAOFactory().getActionDAO().update(action);
	}

	@Override
	public void remove() {
		long sourceId = Long.parseLong((String) getRequestAttributes().get(
				"actionId"));

		DAOFactory.getDAOFactory().getActionDAO().delete(sourceId);

	}

}
