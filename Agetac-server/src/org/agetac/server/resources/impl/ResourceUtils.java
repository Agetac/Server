package org.agetac.server.resources.impl;

import java.io.IOException;
import java.util.List;

import org.agetac.common.model.impl.Agent;
import org.agetac.common.model.impl.Intervention;
import org.agetac.server.db.PersistenceManagerProxy;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;

import com.google.gson.Gson;

public class ResourceUtils {
	public static <T> Representation getResource(Class<T> cls, String uid) {

		if (StringUtils.isNullOrEmpty(uid)) {
			T obj = PersistenceManagerProxy.getInstance().getSingle(cls, uid);
			if (obj == null) {
				return null;
			} else {
				return new JsonRepresentation(new Gson().toJson(obj));
			}
		} else {

			List<T> lst = PersistenceManagerProxy.getInstance().getList(cls);
			return new JsonRepresentation(new Gson().toJson(lst));
		}
	}

	public static <T> Representation putResource(Class<T> cls,
			Representation representation) throws ResourceException {

		String json;
		try {
			json = representation.getText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ResourceException(e);
		}

		T obj = new Gson().fromJson(json, cls);

		PersistenceManagerProxy.getInstance().put(obj);

		return new JsonRepresentation(new Gson().toJson(obj));
	}

	public static <T> void deleteResource(Class<T> cls, String uniqueID) {
		PersistenceManagerProxy.getInstance().delete(cls, uniqueID);
	}
}
