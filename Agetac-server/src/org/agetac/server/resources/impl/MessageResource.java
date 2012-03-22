package org.agetac.server.resources.impl;

import org.agetac.common.model.impl.Message;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public class MessageResource extends BaseServerResource {

	@Get("json")
	public Representation toJSON() {
		return getOneToJson(Message.class);
	}
}
