package org.agetac.server.resources.impl;

import org.agetac.common.model.impl.Intervention;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public class InterventionsResource extends AbstractServerResource {

	@Get("json")
	public Representation toJSON() {
		return getJsonList(Intervention.class);
	}
}
