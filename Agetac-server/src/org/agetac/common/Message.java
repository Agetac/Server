package org.agetac.common;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

public class Message {
	private String uniqueID;
	private String message;
	private String date;

	public Message(String uniqueID, String message, String date) {

		this.uniqueID = uniqueID;
		this.message = message;
		this.date = date;
	}

	public Message(JSONObject json) {
		try {
			this.setUniqueID(json.getString("uniqueID"));
			this.message = json.getString("message");
			this.date = json.getString("date");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
	
	/**
	 * Convert this object to a JSON object for representation
	 */
	public String toJson() {
		
		JSONObject json = new JSONObject();
		try {
			json.put("uniqueID", this.uniqueID);
			json.put("message", this.message);
			json.put("date", this.date);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		System.out.println("test "+json.toString());
		
		return json.toString();
	}

	@Override
	public String toString() {
		return "Message [uniqueID=" + uniqueID + ", message=" + message
				+ ", date=" + date + "]";
	}
}
