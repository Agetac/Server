package org.agetac.common;

import org.json.JSONException;
import org.json.JSONObject;

public class Position {
	private double longitude;
	private double latitude;

	public Position(double longitude, double latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
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

	public String toJson() {
		JSONObject json = new JSONObject();
		try {
			json.append("longitude", this.longitude);
			json.append("latitude", this.latitude);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}
}
