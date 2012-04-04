package org.agetac.server.entities;

import java.util.Collection;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class InterventionEntity {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long id;

	@Persistent(mappedBy = "intervention", defaultFetchGroup="true")
	private Collection<VehicleDemandEntity> vehicleDemands;

	/* TODO Need to figure out how to handle this relationship. */
	private Collection<VehicleEntity> vehicles;

	@Persistent(mappedBy = "intervention", defaultFetchGroup="true")
	private Collection<MessageEntity> messages;

	@Persistent(mappedBy = "intervention", defaultFetchGroup="true")
	private Collection<TargetEntity> targets;

	@Persistent(mappedBy = "intervention", defaultFetchGroup="true")
	private Collection<SourceEntity> sources;

	@Persistent(mappedBy = "intervention", defaultFetchGroup="true")
	private Collection<VictimEntity> victims;

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

	public Collection<VehicleEntity> getVehicles() {
		return vehicles;
	}

	public long getId() {
		return id;
	}

	public Collection<VictimEntity> getVictims() {
		return victims;
	}
}
