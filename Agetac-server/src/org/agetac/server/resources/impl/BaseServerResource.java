package org.agetac.server.resources.impl;

import java.io.IOException;
import java.util.List;

import org.agetac.server.db.SimpleDAO;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public abstract class BaseServerResource extends ServerResource {

	public <T> Representation getOneToJson(Class<T> cls) {
		String uid = (String) this.getRequestAttributes().get("uid");
		T obj = SimpleDAO.getInstance().getOne(cls, uid);
		String json = new Gson().toJson(obj);
		Representation r = new JsonRepresentation(json);
		return r;
	}

	public <T> Representation getManyToJson(Class<T> cls) {
		List<T> obj = SimpleDAO.getInstance().getMany(cls);
		String json = new Gson().toJson(obj);
		Representation r = new JsonRepresentation(json);
		return r;
	}

	public <T> Representation deleteOne(Class<T> cls) {
		String uid = (String) this.getRequestAttributes().get("uid");
		SimpleDAO.getInstance().delete(cls, uid);

		return null;
	}

	public <T> Representation updateFromJson(Class<T> cls,
			Representation representation) {
		T obj;
		try {
			obj = new Gson().fromJson(representation.getReader(), cls);
		} catch (JsonSyntaxException e) {
			return null;
		} catch (IOException e) {
			return null;
		}

		SimpleDAO.getInstance().update(obj);

		return null;
	}

	public <T> Representation addFromJson(Class<T> cls,
			Representation representation) {
		T obj;
		try {
			obj = new Gson().fromJson(representation.getReader(), cls);
		} catch (JsonSyntaxException e) {
			return null;
		} catch (IOException e) {
			return null;
		}

		// Updates the objects ID.
		SimpleDAO.getInstance().add(obj);

		String json = new Gson().toJson(obj);
		Representation r = new JsonRepresentation(json);
		return r;
	}
}
