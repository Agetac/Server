package org.agetac.server.resources;

import org.agetac.common.dto.VictimDTO;
import org.agetac.common.resources.VictimResource;
import org.agetac.server.db.InterventionDAO;
import org.agetac.server.db.InterventionDAOImpl;
import org.agetac.server.db.VictimDAOImpl;
import org.restlet.resource.ServerResource;

public class VictimResourceImpl extends ServerResource implements
		VictimResource {

	@Override
	public VictimDTO add(VictimDTO victim) {
		InterventionDAO dao = new InterventionDAOImpl();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		dao.add(interId, victim);

		return victim;
	}

	@Override
	public void update(VictimDTO victim) {
		VictimDAOImpl.getInstance().update(victim);
	}

	@Override
	public void remove() {
		long victimId = Long.parseLong((String) getRequestAttributes().get(
				"victimId"));

		VictimDAOImpl.getInstance().delete(victimId);

	}

}
