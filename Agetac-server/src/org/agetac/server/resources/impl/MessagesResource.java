package org.agetac.server.resources.impl;

import org.agetac.common.model.impl.Message;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public class MessagesResource extends BaseServerResource {

	@Get("json")
	public Representation toJSON() {
		return getManyToJson(Message.class);
	}
}
