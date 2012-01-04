package org.agetac.common;

public class Cible {
	private Position position;

	public Cible(Position position) {
		super();
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public String toString() {
		 StringBuffer sb = new StringBuffer();
		 sb.append("position:");
		 sb.append(this.position);
		 return sb.toString();
	}
}
