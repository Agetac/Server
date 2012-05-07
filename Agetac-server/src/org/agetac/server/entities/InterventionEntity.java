package org.agetac.server.entities;

import java.util.Collection;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.agetac.common.dto.VehicleDTO;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class InterventionEntity {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long id;

	@Persistent(mappedBy = "intervention", defaultFetchGroup = "true")
	private Collection<VehicleDemandEntity> vehicleDemands;

	/* TODO Need to figure out how to handle this relationship. */
	/*
	 * UPDATE 07/05: Well, we don't manage it that well. We should start by
	 * removing the VehicleResource.add(VehicleDTO vehicle) method..
	 */
	@Persistent(mappedBy = "intervention", defaultFetchGroup = "true")
	private Collection<VehicleEntity> vehicles;

	@Persistent(mappedBy = "intervention", defaultFetchGroup = "true")
	private Collection<MessageEntity> messages;

	@Persistent(mappedBy = "intervention", defaultFetchGroup = "true")
	private Collection<TargetEntity> targets;

	@Persistent(mappedBy = "intervention", defaultFetchGroup = "true")
	private Collection<SourceEntity> sources;

	@Persistent(mappedBy = "intervention", defaultFetchGroup = "true")
	private Collection<ActionEntity> actions;

	@Persistent(mappedBy = "intervention", defaultFetchGroup = "true")
	private Collection<VictimEntity> victims;

	private PositionEntity position;
	private String name;

	public Collection<VehicleDemandEntity> getVehicleDemands() {
		return vehicleDemands;
	}

	public Collection<MessageEntity> getMessages() {
		return messages;
	}

	public Collection<TargetEntity> getTargets() {
		return targets;
	}

	public Collection<SourceEntity> getSources() {
		return sources;
	}

	public Collection<ActionEntity> getActions() {
		return actions;
	}

	public Collection<VehicleEntity> getVehicles() {
		return vehicles;
	}

	public long getId() {
		return id;
	}

	public Collection<VictimEntity> getVictims() {
		return victims;
	}

	public PositionEntity getPosition() {
		return position;
	}

	public void setPosition(PositionEntity position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void update(InterventionEntity newInter) {
		this.actions = newInter.getActions();
		this.messages = newInter.getMessages();
		this.targets = newInter.getTargets();
		this.sources = newInter.getSources();
		this.victims = newInter.getVictims();
		this.vehicleDemands = newInter.getVehicleDemands();
		this.vehicles = newInter.getVehicles();
		this.position = newInter.getPosition();
		this.name = newInter.getName();
	}
}
