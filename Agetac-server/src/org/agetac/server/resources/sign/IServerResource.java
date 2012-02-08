package org.agetac.server.resources.sign;

import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

public interface IServerResource {
	
	@Get
	public Representation getResource() throws Exception;
	@Put
	public Representation putResource(Representation representation) throws Exception;
	@Post
	public Representation postResource(Representation representation) throws Exception;
	@Delete
	public Representation deleteResource();
	
}
