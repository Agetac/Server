package org.agetac.model.impl;

import org.agetac.model.sign.IJsonable;
import org.json.JSONException;
import org.json.JSONObject;

public class Action implements IJsonable{
	private String uniqueID;
	private Position position;

	public Action(String uid, Position position) {
		this.position = position;
		this.uniqueID = uid;
	}
	
	public Action(JSONObject json){
		try {
			this.position = new Position(json.getJSONObject("position"));
			this.setUniqueID(json.getString("uniqueID"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public String getUniqueID() {
		return this.uniqueID;
	}

	public void setUniqueID(String uid) {
		this.uniqueID = uid;
	}
	
	public String toString() {
		 StringBuffer sb = new StringBuffer();
		 sb.append("position:");
		 sb.append(this.position);
		 return sb.toString();
	}
	
	public JSONObject toJson(){
		JSONObject json = new JSONObject();
		try {
			json.put("uniqueID", this.uniqueID);
			json.put("position", this.position.toJson());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	public IJsonable fromJson(JSONObject json) {
		return new Action(json);
	}
}
