package org.agetac.server.resources;

import org.agetac.common.dto.TargetDTO;
import org.agetac.common.resources.TargetResource;
import org.agetac.server.db.InterventionDAO;
import org.agetac.server.db.TargetDAO;
import org.restlet.resource.ServerResource;

public class TargetResourceImpl extends ServerResource implements
		TargetResource {

	@Override
	public TargetDTO add(TargetDTO target) {
		InterventionDAO dao = new InterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		dao.addTarget(interId, target);
		
		return target;
	}

	@Override
	public void update(TargetDTO target) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove() {
		long targetId = Long.parseLong((String) getRequestAttributes().get(
				"targetId"));

		TargetDAO.getInstance().delete(targetId);

	}

}
