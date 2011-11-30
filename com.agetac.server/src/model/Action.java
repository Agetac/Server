package model;

public class Action {
	private Position position;

	public Action(Position position) {
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
