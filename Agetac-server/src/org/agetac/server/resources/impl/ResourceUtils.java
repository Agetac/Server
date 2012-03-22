package org.agetac.server.resources.impl;

import java.io.IOException;
import java.util.List;

import org.agetac.server.db.SimpleDAO;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;

import com.google.gson.Gson;

public class ResourceUtils {
	public static <T> Representation getResource(Class<T> cls, String uid) {

		if (StringUtils.isNullOrEmpty(uid)) {
			T obj = SimpleDAO.getInstance().getOne(cls, uid);
			if (obj == null) {
				return null;
			} else {
				return new JsonRepresentation(new Gson().toJson(obj));
			}
		} else {

			List<T> lst = SimpleDAO.getInstance().getMany(cls);
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

		SimpleDAO.getInstance().update(obj);

		return new JsonRepresentation(new Gson().toJson(obj));
	}

	public static <T> void deleteResource(Class<T> cls, String uniqueID) {
		SimpleDAO.getInstance().delete(cls, uniqueID);
	}
}
