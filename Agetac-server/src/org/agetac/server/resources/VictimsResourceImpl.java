package org.agetac.server.resources;

import java.util.Collection;

import org.agetac.common.dto.VictimDTO;
import org.agetac.common.resources.VictimsResource;
import org.agetac.server.dao.impl.InterventionDAOImpl;
import org.agetac.server.db.InterventionDAO;
import org.restlet.resource.ServerResource;


public class VictimsResourceImpl extends ServerResource implements
		VictimsResource {

	@Override
	public Collection<VictimDTO> retrieve() {
		InterventionDAO dao = new InterventionDAOImpl();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));

		return dao.retrieveVictims(interId);
	}

}
