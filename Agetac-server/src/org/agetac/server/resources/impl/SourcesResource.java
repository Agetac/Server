package org.agetac.server.resources.impl;

import org.agetac.common.model.impl.Source;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public class SourcesResource extends AbstractServerResource {
	@Get("json")
	public Representation toJSON() {
		return getJsonList(Source.class);
	}
}
