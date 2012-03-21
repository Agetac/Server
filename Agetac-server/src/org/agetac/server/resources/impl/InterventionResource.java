package org.agetac.server.resources.impl;

import org.agetac.common.model.impl.Intervention;
import org.agetac.server.resources.sign.DeleteCapable;
import org.agetac.server.resources.sign.GetCapable;
import org.agetac.server.resources.sign.UpdateCapable;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public class InterventionResource extends BaseServerResource implements
		GetCapable, UpdateCapable, DeleteCapable {

	@Delete
	public void removeItem() {
		deleteItem(Intervention.class);

	}

	@Put
	public void updateItem(Representation entity) throws CommunicationException {
		updateItemFromJson(Intervention.class, entity);

	}

	@Get
	public Representation getItem() {
		return getJsonSingle(Intervention.class);
	}

}
