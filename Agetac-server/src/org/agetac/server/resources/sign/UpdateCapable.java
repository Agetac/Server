package org.agetac.server.resources.sign;

import org.agetac.server.resources.impl.CommunicationException;
import org.restlet.representation.Representation;

public interface UpdateCapable {

	public void updateItem(Representation entity) throws CommunicationException;

}