package org.agetac.server.resources;

import org.agetac.common.dto.InterventionDTO;
import org.agetac.common.resources.InterventionResource;
import org.agetac.server.db.InterventionDAOImpl;
import org.agetac.server.entities.InterventionEntity;
import org.modelmapper.ModelMapper;
import org.restlet.resource.ServerResource;

public class InterventionResourceImpl extends ServerResource implements
		InterventionResource {

	@Override
	public InterventionDTO add(InterventionDTO interventionDTO) {

		ModelMapper modelMapper = new ModelMapper();
		InterventionEntity entity = modelMapper.map(interventionDTO,
				InterventionEntity.class);

		InterventionDAOImpl dao = new InterventionDAOImpl();
		dao.add(entity);
		
		interventionDTO.setId(entity.getId());
		return interventionDTO;
	}

	@Override
	public InterventionDTO retrieve() {
		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));

		InterventionDTO dto = new InterventionDAOImpl().retrieveOne(interId);
		return dto;
	}

	@Override
	public void update(InterventionDTO intervention) {
		InterventionDAOImpl.getInstance().update(intervention);
	}

}
