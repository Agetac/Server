package org.agetac.server.entities;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class BarrackEntity {

	private String name;
	private PositionEntity position;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long id;

	public BarrackEntity() {
	}

	public BarrackEntity(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public PositionEntity getPosition() {
		return position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPosition(PositionEntity p) {
		this.position = p;

	}
}
