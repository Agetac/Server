package org.agetac.model.impl;

import org.agetac.model.sign.AbstractModel;
import org.agetac.model.sign.IJsonable;
import org.json.JSONObject;

public class Action extends AbstractModel {

	public Action(String uid, Position position) {
		super(uid, null, position);
	}

	public Action(JSONObject json) {
		super(json);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("position:");
		sb.append(this.position);
		return sb.toString();
	}

	public JSONObject toJson() {
		return super.toJson();
	}

	public IJsonable fromJson(JSONObject json) {
		return new Action(json);
	}
}
