package org.agetac.common;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@PersistenceCapable
public class Intervention {

	private Position lieu;

	private List<Moyen> moyens;
	private List<Cible> cibles;
	private List<Source> sources;
	private List<Action> actions;
	private List<Message> messages;
	private List<Implique> impliques;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	private String uniqueID;

	public Intervention(String uniqueID) {
		this();

		this.uniqueID = uniqueID;
		this.lieu = new Position(0, 0);
		
	}

	public Intervention() {
		this.moyens = new ArrayList<Moyen>();
		this.cibles = new ArrayList<Cible>();
		this.sources = new ArrayList<Source>();
		this.actions = new ArrayList<Action>();
		this.messages = new ArrayList<Message>();
		this.impliques = new ArrayList<Implique>();
	}

	public Intervention(JSONObject json) {

		this.moyens = new ArrayList<Moyen>();
		this.cibles = new ArrayList<Cible>();
		this.sources = new ArrayList<Source>();
		this.actions = new ArrayList<Action>();
		this.messages = new ArrayList<Message>();
		this.impliques = new ArrayList<Implique>();

		try {
			this.uniqueID = (String) json.get("uniqueID");

			this.lieu = new Position(json.getJSONObject("lieu"));

			JSONArray jsar = json.getJSONArray("moyens");
			for (int i = 0; i < jsar.length(); i++) {
				moyens.add(new Moyen(jsar.getJSONObject(i)));
			}

			jsar = json.getJSONArray("cibles");
			for (int i = 0; i < jsar.length(); i++) {
				cibles.add(new Cible(jsar.getJSONObject(i)));
			}

			jsar = json.getJSONArray("sources");
			for (int i = 0; i < jsar.length(); i++) {
				sources.add(new Source(jsar.getJSONObject(i)));
			}

			jsar = json.getJSONArray("actions");
			for (int i = 0; i < jsar.length(); i++) {
				actions.add(new Action(jsar.getJSONObject(i)));
			}

			jsar = json.getJSONArray("messages");
			for (int i = 0; i < jsar.length(); i++) {
				messages.add(new Message(jsar.getJSONObject(i)));
			}

			jsar = json.getJSONArray("impliques");
			for (int i = 0; i < jsar.length(); i++) {
				impliques.add(new Implique(jsar.getJSONObject(i)));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void demandeMoyen() {

	}

	public String toString() {
		return "Intervention [moyens=" + moyens + ", lieu=" + lieu + "]";
	}

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		try {

			json.put("uniqueID", this.uniqueID);
			json.put("lieu", this.lieu.toJson());
			json.put("moyens", this.moyens);
			json.put("cibles", this.cibles);
			json.put("sources", this.sources);
			json.put("actions", this.actions);
			json.put("messages", this.messages);
			json.put("impliques", this.impliques);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return json;
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

	public String getUniqueID() {
		return this.uniqueID;
	}

}
