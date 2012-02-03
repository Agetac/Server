package org.agetac.model.impl;

import org.agetac.model.sign.IJsonable;
import org.json.JSONException;
import org.json.JSONObject;

public class Source implements IJsonable {

	private String uniqueID;
	private Position position;

	public Source(String uniqueId, Position position) {
		this.uniqueID = uniqueId;
		this.position = position;
	}
	
	public Source(JSONObject json){
		try {
			this.setUniqueID(json.getString("uniqueID"));
			this.position = new Position(json.getJSONObject("position"));
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
			json.put("position", position.toJson());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public IJsonable fromJson(JSONObject json) {
		return new Source(json);
	}

	
	public void setUniqueID(String uniqueId) {
		this.uniqueID = uniqueId;
	}
	public String getUniqueID() {
		return this.uniqueID;
	}
}
