package org.agetac.server.resources.impl;

import org.agetac.common.model.impl.Cible;
import org.restlet.representation.Representation;

public class CiblesResource extends BaseServerResource {

	@Override
	public Representation get() {
		return getManyToJson(Cible.class);
	}

	@Override
	public Representation post(Representation entity) {
		return addFromJson(Cible.class, entity);
	}
}
