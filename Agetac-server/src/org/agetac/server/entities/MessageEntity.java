package org.agetac.server.entities;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class MessageEntity {

	private InterventionEntity intervention;
	
	private String text;
	
	private String getText() {
		return text;
	}
}
