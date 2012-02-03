package org.agetac.model.sign;

import org.json.JSONObject;

public interface IJsonable {

	public IJsonable fromJson(JSONObject json);

	public JSONObject toJson();

}
