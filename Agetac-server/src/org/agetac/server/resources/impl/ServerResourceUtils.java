package org.agetac.server.resources.impl;

import java.util.List;

import org.agetac.server.db.SimpleDAO;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;

public abstract class ServerResourceUtils extends ServerResource {

	public <T> Representation getJsonSingle(Class<T> cls) {
		String uid = (String) this.getRequestAttributes().get("uid");
		T obj = SimpleDAO.getInstance().getOne(cls, uid);
		String json = new Gson().toJson(obj);
		Representation r = new JsonRepresentation(json);
		return r;
	}

	public <T> Representation getJsonList(Class<T> cls) {
		List<T> obj = SimpleDAO.getInstance().getMany(cls);
		String json = new Gson().toJson(obj);
		Representation r = new JsonRepresentation(json);
		return r;
	}
}
