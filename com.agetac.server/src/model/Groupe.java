package model;

import java.util.List;

public class Groupe {
	private Agent chef;
	private List<Agent> membre;
	private List<Moyen> moyens;

	public Groupe(Agent chef, List<Agent> membre, List<Moyen> moyens) {
		super();
		this.chef = chef;
		this.membre = membre;
		this.moyens = moyens;
	}

	public List<Moyen> getMoyens() {
		return moyens;
	}

	public void setMoyens(List<Moyen> moyens) {
		this.moyens = moyens;
	}

	public Agent getChef() {
		return chef;
	}

	public void setChef(Agent chef) {
		this.chef = chef;
	}

	public List<Agent> getMembre() {
		return membre;
	}

	public void setMembre(List<Agent> membre) {
		this.membre = membre;
	}
}
