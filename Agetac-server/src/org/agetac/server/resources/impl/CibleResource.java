package org.agetac.server.resources.impl;

import org.agetac.common.model.impl.Cible;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public class CibleResource extends AbstractServerResource {

	@Get("json")
	public Representation toJSON() {
		return getJsonSingle(Cible.class);
	}
}
