package org.agetac.server.entities;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.agetac.server.db.InterventionDAO;
import org.agetac.server.entities.VehicleEntity.VehicleType;

@PersistenceCapable
public class VehicleDemandEntity {

	public enum DemandState {ASKED, REFUSED, ACCEPTED}
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long id;
	
	private Date timestamp;
	private DemandState state;
	private PositionEntity position;
	private VehicleType type;
	private String name;
	
	private String groupeHoraire;
	
	public String getGroupeHoraire() {
		return groupeHoraire;
	}

	public void setGroupeHoraire(String groupeHoraire) {
		this.groupeHoraire = groupeHoraire;
	}

	private InterventionEntity intervention;
	
	/** default vehicle id associated with the demand is unknown (-1)*/
	private int vehicleId = -1;
	
	public VehicleDemandEntity() {
		
	}
	
	public VehicleDemandEntity(DemandState state, VehicleType cat, PositionEntity p, Date date) {
		this.state = state;
		this.type = cat;
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

	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}
	
	public void update(VehicleDemandEntity demand) {
		this.name = demand.getName();
		this.position = demand.getPosition();
		this.state = demand.getState();
		this.timestamp = demand.getTimestamp();
		this.type = demand.getType();
		this.vehicleId = demand.getVehicleId();
	}
}
