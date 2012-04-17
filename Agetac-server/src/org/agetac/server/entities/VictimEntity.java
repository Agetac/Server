package org.agetac.server.entities;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.agetac.common.dto.VictimDTO;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class VictimEntity {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long id;

	private InterventionEntity intervention;
	
	public long getId() {
		return id;
	}

	private String name = "";

	public VictimEntity() {
	}

	public VictimEntity(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void update(VictimEntity victim) {
		this.name = victim.getName();
	}
}
