package org.agetac.server.entities;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class TargetEntity {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long id;
	
	public enum TargetType {WATER, FIRE, CHEM, HUMAN}
	
	private InterventionEntity intervention;
	
	private TargetType type;
	private PositionEntity position;
	private String name = "";
	
	public TargetEntity() {}
	
	public TargetEntity(TargetType type) {
	}
	
	public long getId() {
		return id;
	}
	
	public TargetEntity(String name, TargetType type, PositionEntity p) {
		this.name = name;
		this.type = type;
		this.position = p;
	}

	public TargetType getType() {
		return type;
	}

	public void setType(TargetType type) {
		this.type = type;
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
	
	public void update(TargetEntity target) {
		this.type = target.getType();
		this.position = target.getPosition();
		this.name = target.getName();
	}
}
