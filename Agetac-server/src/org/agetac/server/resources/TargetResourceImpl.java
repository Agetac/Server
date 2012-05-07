package org.agetac.server.resources;

import org.agetac.common.dto.TargetDTO;
import org.agetac.common.resources.TargetResource;
import org.agetac.server.db.InterventionDAO;
import org.agetac.server.db.InterventionDAOImpl;
import org.agetac.server.db.TargetDAOImpl;
import org.restlet.resource.ServerResource;

public class TargetResourceImpl extends ServerResource implements
		TargetResource {

	@Override
	public TargetDTO add(TargetDTO target) {
		InterventionDAO dao = new InterventionDAOImpl();

		long interId = Long.parseLong((String) getRequestAttributes().get("interId"));
		target = dao.addTarget(interId, target);
		
		return target;
	}

	@Override
	public void update(TargetDTO target) {
		TargetDAOImpl.getInstance().update(target);
	}

	@Override
	public void remove() {
		long targetId = Long.parseLong((String) getRequestAttributes().get("targetId"));

		TargetDAOImpl.getInstance().delete(targetId);

	}

}
