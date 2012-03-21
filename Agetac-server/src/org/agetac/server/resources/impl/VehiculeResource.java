package org.agetac.server.resources.impl;

import org.agetac.common.model.impl.Vehicule;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public class VehiculeResource extends BaseServerResource {
	@Get("json")
	public Representation toJSON() {
		return getJsonSingle(Vehicule.class);
	}
}
