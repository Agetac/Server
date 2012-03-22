package org.agetac.server.resources.impl;

import org.agetac.common.model.impl.Intervention;
import org.restlet.representation.Representation;

public class InterventionResource extends BaseServerResource {

	@Override
	public Representation delete() {
		return deleteOne(Intervention.class);
	}

	@Override
	public Representation put(Representation entity) {
		return updateFromJson(Intervention.class, entity);
	}

	@Override
	public Representation get() {
		return getOneToJson(Intervention.class);
	}

}
