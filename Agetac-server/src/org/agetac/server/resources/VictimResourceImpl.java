package org.agetac.server.resources;

import org.agetac.common.dto.VictimDTO;
import org.agetac.common.resources.VictimResource;
import org.agetac.server.dao.DAOFactory;
import org.agetac.server.dao.InterventionDAO;
import org.restlet.resource.ServerResource;

public class VictimResourceImpl extends ServerResource implements
		VictimResource {

	@Override
	public VictimDTO add(VictimDTO victim) {
		InterventionDAO dao = DAOFactory.getDAOFactory().getInterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		dao.add(interId, victim);

		return victim;
	}

	@Override
	public void update(VictimDTO victim) {
		DAOFactory.getDAOFactory().getVictimDAO().update(victim);
	}

	@Override
	public void remove() {
		long victimId = Long.parseLong((String) getRequestAttributes().get(
				"victimId"));

		DAOFactory.getDAOFactory().getVictimDAO().delete(victimId);

	}

}
