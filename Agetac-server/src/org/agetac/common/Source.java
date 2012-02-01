package org.agetac.common;

import org.json.JSONException;
import org.json.JSONObject;

public class Source implements IJsonable{
	private Position position;

	public Source(Position position) {
		this.position = position;
	}
	
	public Source(JSONObject json){
		try {
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
}
