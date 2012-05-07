package org.agetac.server.resources;

import org.agetac.common.dto.MessageDTO;
import org.agetac.common.resources.MessageResource;
import org.agetac.server.db.InterventionDAO;
import org.restlet.resource.ServerResource;


public class MessageResourceImpl extends ServerResource implements
		MessageResource {

	@Override
	public MessageDTO add(MessageDTO message) {
		InterventionDAO dao = new InterventionDAO();

		long interId = Long.parseLong((String) getRequestAttributes().get(
				"interId"));
		message = dao.addMessage(interId, message);
		
		return message;
	}
	
	

}
