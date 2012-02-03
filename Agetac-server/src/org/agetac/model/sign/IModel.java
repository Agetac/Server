package org.agetac.model.sign;

import org.agetac.model.impl.Position;

public interface IModel {

	public String getUniqueID();
	
	public String getName();
	
	public Position getPosition();
	
	public void setUniqueID(String uid);
	
	public void setName(String name);
	
	public void setPosition(Position position);
}
