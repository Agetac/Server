package org.agetac.server.resources.impl;

import org.agetac.common.model.impl.Cible;
import org.agetac.server.resources.sign.DeleteCapable;
import org.agetac.server.resources.sign.GetCapable;
import org.agetac.server.resources.sign.UpdateCapable;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public class CibleResource extends BaseServerResource implements
		GetCapable, UpdateCapable, DeleteCapable {

	/**
	 * Handle GET requests: get an existing item.
	 * 
	 * @return
	 */
	@Get("json")
	public Representation getItem() {
		return getJsonSingle(Cible.class);
	}

	/**
	 * Handle PUT requests: update an existing item.
	 * 
	 * @throws CommunicationException
	 */
	@Put("json")
	public void updateItem(Representation entity) throws CommunicationException {
		updateItemFromJson(Cible.class, entity);
	}

	/**
	 * Handle DELETE requests: delete an existing item.
	 */
	@Delete
	public void removeItem() {
		deleteItem(Cible.class);
	}
}
