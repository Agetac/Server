package org.agetac.server.resources.impl;

import org.agetac.common.model.impl.Intervention;
import org.agetac.server.resources.sign.CreateCapable;
import org.agetac.server.resources.sign.GetCapable;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

public class InterventionsResource extends BaseServerResource implements
		GetCapable, CreateCapable {

	@Get("json")
	public Representation getItem() {
		return getJsonList(Intervention.class);
	}

	@Post("json")
	public Representation createItem(Representation entity)
			throws CommunicationException {
		return createItemFromJson(Intervention.class, entity);
	}
}
