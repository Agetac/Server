package org.agetac.server.entities;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class ActionEntity {

	public enum ActionType {WATER, FIRE, HUMAN}
	
	private String name = "";
	private ActionType type;
	private PositionEntity position;
	private PositionEntity origin;
	private PositionEntity aim;
	
	private InterventionEntity intervention;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long id;
	
	public ActionEntity() {
		this.type = ActionType.FIRE;
		this.position = new PositionEntity();
		this.aim = new PositionEntity();
	}
	
	public ActionEntity(String n, ActionType t, PositionEntity p, PositionEntity a) {
		this.name = n;
		this.type = t;
		this.position = p;
		this.aim = a;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ActionType getType() {
		return type;
	}

	public void setType(ActionType type) {
		this.type = type;
	}

	public PositionEntity getPosition() {
		return position;
	}

	public void setPosition(PositionEntity position) {
		this.position = position;
	}

	public PositionEntity getAim() {
		return aim;
	}

	public void setAim(PositionEntity aim) {
		this.aim = aim;
	}

	public PositionEntity getOrigin() {
		return origin;
	}

	public void setOrigin(PositionEntity origin) {
		this.origin = origin;
	}
	
	public long getId() {
		return id;
	}
	
	public void update(ActionEntity action) {
		this.aim = action.getAim();
		this.name = action.getName();
		this.origin = action.getOrigin();
		this.position = action.getPosition();
		this.type = action.getType();
	}
}
