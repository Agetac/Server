package org.agetac.server.entities;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class PositionEntity {

	private double latitude;
	private double longitude;
	private boolean isKnown = false;
	
	public PositionEntity() {}
	
	public PositionEntity(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public boolean isKnown() {
		return isKnown;
	}

	public void setKnown(boolean isKnown) {
		this.isKnown = isKnown;
	}
	
}
