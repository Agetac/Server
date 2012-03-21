package org.agetac.server.resources.impl;

import org.agetac.common.model.impl.Source;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public class SourceResource extends BaseServerResource {
	@Get("json")
	public Representation toJSON() {
		return getJsonSingle(Source.class);
	}
}
