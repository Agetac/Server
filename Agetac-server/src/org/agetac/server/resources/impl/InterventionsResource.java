package org.agetac.server.resources.impl;

import org.agetac.common.model.impl.Intervention;
import org.restlet.representation.Representation;

public class InterventionsResource extends BaseServerResource {

	@Override
	public Representation get() {
		return getManyToJson(Intervention.class);
	}

	@Override
	public Representation post(Representation entity) {
		return addFromJson(Intervention.class, entity);
	}
}
