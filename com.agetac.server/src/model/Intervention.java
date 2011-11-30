package model;

import java.util.ArrayList;
import java.util.List;

public class Intervention {

	private Position lieu;

	private List<Moyen> moyens;
	private List<Cible> cibles;
	private List<Source> sources;
	private List<Action> actions;
	private List<Message> messages;
	private List<Implique> impliques;

	public Intervention(Position lieu) {
		super();
		this.lieu = lieu;
		this.moyens = new ArrayList<Moyen>();
		this.cibles = new ArrayList<Cible>();
		this.sources = new ArrayList<Source>();
		this.actions = new ArrayList<Action>();
		this.messages = new ArrayList<Message>();
		this.impliques = new ArrayList<Implique>();
	}

	
	public void demandeMoyen() {

	}

	public String toString() {
		return "Intervention [moyens=" + moyens + ", lieu=" + lieu + "]";
	}
	public String toJson() {
		return "Intervention [moyens=" + moyens + ", lieu=" + lieu + "]";
	}
	
	/*
	 * GETTER & SETTER
	 */
	
	public Position getLieu() {
		return lieu;
	}

	public void setLieu(Position lieu) {
		this.lieu = lieu;
	}

	public List<Moyen> getMoyens() {
		return moyens;
	}

	public void setMoyens(List<Moyen> moyens) {
		this.moyens = moyens;
	}

	public List<Cible> getCibles() {
		return cibles;
	}

	public void setCibles(List<Cible> cibles) {
		this.cibles = cibles;
	}

	public List<Source> getSources() {
		return sources;
	}

	public void setSources(List<Source> sources) {
		this.sources = sources;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<Implique> getImpliques() {
		return impliques;
	}

	public void setImpliques(List<Implique> impliques) {
		this.impliques = impliques;
	}


}
