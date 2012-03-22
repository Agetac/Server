package org.agetac.server.resources.impl;

import org.agetac.common.model.impl.Cible;
import org.restlet.representation.Representation;

public class CibleResource extends BaseServerResource {

	@Override
	public Representation get() {
		return getOneToJson(Cible.class);
	}

	@Override
	public Representation put(Representation entity) {
		return updateFromJson(Cible.class, entity);
	}

	@Override
	public Representation delete() {
		return deleteOne(Cible.class);
	}
}
