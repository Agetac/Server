package org.agetac.model.sign;

import org.agetac.model.impl.Position;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbstractModel implements IJsonable, IModel {

	protected String uniqueID;
	protected String name;
	protected Position position;
	
	public AbstractModel(String uid, String name, Position position) {
		this.uniqueID = uid;
		this.name = name;
		this.position = position;
	}
	
	public AbstractModel(JSONObject json) {
		try {
			this.uniqueID = json.getString("uniqueID");
			this.name = json.getString("nom");
			this.position = new Position(json.getJSONObject("position"));
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Convert this object to a JSON object for representation
	 */
	@Override
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		
		try {
			json.put("uniqueID", this.uniqueID);
			json.put("nom", this.name);
			json.put("position", this.position.toJson());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return json;
	}

	@Override
	public String getUniqueID() {
		return uniqueID;
	}

	@Override
	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public void setPosition(Position position) {
		this.position = position;
	}
}
