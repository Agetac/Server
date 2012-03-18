package org.agetac.server.resources.impl;

import org.agetac.common.model.impl.Message;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public class MessageResource extends AbstractServerResource {

	@Get("json")
	public Representation toJSON() {
		return getJsonSingle(Message.class);
	}
}
