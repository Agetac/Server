package org.agetac.server.resources;

import org.agetac.common.dto.InterventionDTO;
import org.agetac.common.resources.InterventionResource;
import org.agetac.server.db.InterventionDAO;
import org.agetac.server.entities.InterventionEntity;
import org.modelmapper.ModelMapper;
import org.restlet.resource.ServerResource;

public class InterventionResourceImpl extends ServerResource implements
		InterventionResource {

	@Override
	public void add(InterventionDTO interventionDTO) {

		ModelMapper modelMapper = new ModelMapper();
		InterventionEntity entity = modelMapper.map(interventionDTO,
				InterventionEntity.class);

		InterventionDAO dao = new InterventionDAO();
		dao.add(entity);
	}

	@Override
	public InterventionDTO retrieve() {
		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));

		return new InterventionDAO().retrieveOne(interId);
	}

}
