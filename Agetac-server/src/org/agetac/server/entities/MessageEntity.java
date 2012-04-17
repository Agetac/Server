package org.agetac.server.entities;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class MessageEntity {

	private InterventionEntity intervention;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long id;
	
	private String text;
	
	public String getText() {
		return text;
	}

	public long getId() {
		return id;
	}
}
