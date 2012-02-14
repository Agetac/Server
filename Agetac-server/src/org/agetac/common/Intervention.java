package org.agetac.common;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.xml.bind.annotation.XmlAttribute;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Intervention {

	/*private Position lieu;

	private List<Moyen> moyens;
	private List<Cible> cibles;
	private List<Source> sources;
	private List<Action> actions;
	private List<Message> messages;
	private List<Implique> impliques;*/

	@PrimaryKey
	private String uniqueID;

	public Intervention(String uniqueID) {
		this();

		this.uniqueID = uniqueID;
		//this.lieu = new Position(0, 0);
		
	}

	public Intervention() {
		/*this.moyens = new ArrayList<Moyen>();
		this.cibles = new ArrayList<Cible>();
		this.sources = new ArrayList<Source>();
		this.actions = new ArrayList<Action>();
		this.messages = new ArrayList<Message>();
		this.impliques = new ArrayList<Implique>();*/
	}

	public Intervention(JSONObject json) {

		/*this.moyens = new ArrayList<Moyen>();
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
		}*/
	}

	public void demandeMoyen() {

	}

	/*public String toString() {
		return "Intervention [moyens=" + moyens + ", lieu=" + lieu + "]";
	}*/

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		/*try {

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
		}*/

		return json;
	}

	/*
	 * GETTER & SETTER
	 */

	public Position getLieu() {
		return new Position(0, 0);
	}

	public void setLieu(Position lieu) {
		// this.lieu = lieu;
	}

	public List<Moyen> getMoyens() {
		return null;
	}

	public void setMoyens(List<Moyen> moyens) {
		
	}

	public List<Cible> getCibles() {
		return null;
	}

	public void setCibles(List<Cible> cibles) {
		
	}

	public List<Source> getSources() {
		return null;
	}

	public void setSources(List<Source> sources) {
		
	}

	public List<Action> getActions() {
		return null;
	}

	public void setActions(List<Action> actions) {
		
	}

	public List<Message> getMessages() {
		return null;
	}

	public void setMessages(List<Message> messages) {
		
	}

	public List<Implique> getImpliques() {
		return null;
	}

	public void setImpliques(List<Implique> impliques) {
		
	}

	public String getUniqueID() {
		return this.uniqueID;
	}

}
