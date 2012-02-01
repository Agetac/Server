package org.agetac.common;

import org.json.JSONException;
import org.json.JSONObject;

public class Aptitude implements IJsonable{
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
		return "apt";
	}

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		try {
			
			json.put("grade", this.grade);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public IJsonable fromJson(JSONObject json) {
		return new Aptitude(json);
	}


}
