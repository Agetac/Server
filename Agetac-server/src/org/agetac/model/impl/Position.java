package org.agetac.model.impl;

import org.agetac.model.sign.IJsonable;
import org.json.JSONException;
import org.json.JSONObject;

public class Position implements IJsonable {
	
	private double longitude;
	private double latitude;

	public Position(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public Position(JSONObject json){
		try {
			this.longitude = json.getDouble("longitude");
			this.latitude = json.getDouble("latitude");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		try {
			json.put("longitude", this.longitude);
			json.put("latitude", this.latitude);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public IJsonable fromJson(JSONObject json) {
		return new Position(json);
	}
}
