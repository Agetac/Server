package org.agetac.common;

import org.json.JSONException;
import org.json.JSONObject;

public class Aptitude {
	private String grade;

	public Aptitude(String grade) {
		super();
		this.grade = grade;
	}
	
	public Aptitude(JSONObject json) {
		try {
			
			this.grade = json.getString("grade");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public String toString() {
		/* StringBuffer sb = new StringBuffer();
		 sb.append("grade:");
		 sb.append(this.grade);
		 return sb.toString();*/
		return "apt";
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			
			json.put("grade", this.grade);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
}
