package org.agetac.server.resources;

import java.util.Collection;

import org.agetac.common.dto.InterventionDTO;
import org.agetac.common.resources.InterventionsResource;
import org.agetac.server.db.InterventionDAO;
import org.agetac.server.db.InterventionDAOImpl;
import org.restlet.resource.ServerResource;


public class InterventionsResourceImpl extends ServerResource implements
		InterventionsResource {

	@Override
	public Collection<InterventionDTO> retrieve() {
		InterventionDAO dao = new InterventionDAOImpl();
		return dao.retrieveAll();
	}

}
