package org.agetac.client.api;

import org.agetac.client.exception.BadResponseException;
import org.restlet.representation.Representation;

public interface ServerApi {
	
	public Representation getResource(String resType, String resUniqueID) throws BadResponseException;
	
	public void putResource(String resType, String resUniqueID,	Representation resRepresentation) throws BadResponseException;
	
	public void postResource(String resType, String resUniqueID, Representation resRepresentation) throws BadResponseException;
	
	public void deleteResource(String resType, String resUniqueID) throws BadResponseException;
	
}
