package org.agetac.server.entities;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;

import org.agetac.server.entities.VehicleEntity.VehicleType;

@PersistenceCapable
public class VehicleDemandEntity {

	public enum DemandState {ASKED, REFUSED, ACCEPTED}
	
	private Date timestamp;
	private DemandState state;
	private PositionEntity position;
	private VehicleType category;
	
	private InterventionEntity intervention;
	
	/** default vehicle id associated with the demand is unknown (-1)*/
	private int vehicleId = -1;
	
	public VehicleDemandEntity() {}
	
	public VehicleDemandEntity(DemandState state, VehicleType cat, PositionEntity p, Date date) {
		this.state = state;
		this.category = cat;
		this.position = p;
		this.timestamp = date;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public DemandState getState() {
		return state;
	}

	public void setState(DemandState state) {
		this.state = state;
	}

	public PositionEntity getPosition() {
		return position;
	}

	public void setPosition(PositionEntity position) {
		this.position = position;
	}

	public VehicleType getCategory() {
		return category;
	}

	public void setCategory(VehicleType category) {
		this.category = category;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
}
