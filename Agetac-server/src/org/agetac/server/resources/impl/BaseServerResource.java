package org.agetac.server.resources.impl;

import java.io.IOException;
import java.util.List;

import org.agetac.server.db.PersistenceManagerProxy;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public abstract class BaseServerResource extends ServerResource {

	public <T> Representation getJsonSingle(Class<T> cls) {
		String uid = (String) this.getRequestAttributes().get("uid");
		T obj = PersistenceManagerProxy.getInstance().getSingle(cls, uid);
		String json = new Gson().toJson(obj);
		Representation r = new JsonRepresentation(json);
		return r;
	}

	public <T> Representation getJsonList(Class<T> cls) {
		List<T> obj = PersistenceManagerProxy.getInstance().getList(cls);
		String json = new Gson().toJson(obj);
		Representation r = new JsonRepresentation(json);
		return r;
	}

	public <T> void deleteItem(Class<T> cls) {
		String uid = (String) this.getRequestAttributes().get("uid");
		PersistenceManagerProxy.getInstance().delete(cls, uid);
	}

	public <T> void updateItemFromJson(Class<T> cls,
			Representation representation) throws CommunicationException {
		T obj;
		try {
			obj = new Gson().fromJson(representation.getReader(), cls);
		} catch (JsonSyntaxException e) {
			throw new CommunicationException(e);
		} catch (IOException e) {
			throw new CommunicationException(e);
		}

		PersistenceManagerProxy.getInstance().put(obj);
	}

	public <T> Representation createItemFromJson(Class<T> cls,
			Representation representation) throws CommunicationException {
		T obj;
		try {
			obj = new Gson().fromJson(representation.getText(), cls);
		} catch (JsonSyntaxException e) {
			throw new CommunicationException(e);
		} catch (IOException e) {
			throw new CommunicationException(e);
		}

		// Updates the objects ID.
		PersistenceManagerProxy.getInstance().put(obj);

		String json = new Gson().toJson(obj);
		Representation r = new JsonRepresentation(json);
		return r;
	}
}
