package org.agetac.server.entities;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class SourceEntity {

	public enum SourceType {WATER, FIRE, CHEM}
	
	private SourceType type;
	private PositionEntity position;
	private String name = "";
	
	private InterventionEntity intervention;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long id;
	
	public SourceEntity() {}
	
	public SourceEntity(SourceType type) {
		this.position = new PositionEntity();
	}
	
	public SourceEntity(String name, SourceType type, PositionEntity p) {
		this.name = name;
		this.type = type;
		this.position = p;
	}

	public SourceType getType() {
		return type;
	}

	public void setType(SourceType type) {
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

	public long getId() {
		return id;
	}
	
}
