package org.agetac.server.resources.impl;

import org.agetac.common.model.impl.Cible;
import org.agetac.server.resources.sign.CreateCapable;
import org.agetac.server.resources.sign.GetCapable;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

public class CiblesResource extends BaseServerResource implements GetCapable, CreateCapable {

	/**
	 * Handle GET requests: get an existing item.
	 * 
	 * @return
	 */
	@Get("json")
	public Representation getItem() {
		return getJsonList(Cible.class);
	}

	/**
	 * Handle POST requests: create a new item.
	 * 
	 * @throws CommunicationException
	 */
	@Post("json")
	public Representation createItem(Representation entity)
			throws CommunicationException {
		
		// Should return the updated item (with a UID).
		return createItemFromJson(Cible.class, entity);
	}
}
