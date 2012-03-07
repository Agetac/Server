package org.agetac.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.agetac.client.api.InterventionApi;
import org.agetac.client.exception.BadResponseException;
import org.agetac.model.exception.InvalidJSONException;
import org.agetac.model.impl.Action;
import org.agetac.model.impl.Cible;
import org.agetac.model.impl.Implique;
import org.agetac.model.impl.Message;
import org.agetac.model.impl.Source;
import org.agetac.model.impl.Vehicule;
import org.json.JSONArray;
import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;

public class InterventionConnection implements InterventionApi{

	private String interId;
	private ServerConnection serv;

	public InterventionConnection(String interId, ServerConnection serv) {

		this.interId = interId;
		this.serv = serv;

	}

	public Message getMessage(String msgId) throws BadResponseException {
		Message m = null;
		JsonRepresentation representation = null;

		Representation repr = serv.getResource("intervention/" + interId
				+ "/message", msgId);

		try {
			representation = new JsonRepresentation(repr);
			m = new Message(representation.getJsonObject());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}


		return m;
	}

	public List<Message> getMessages() throws BadResponseException {

		List<Message> messages = new ArrayList<Message>();
		JsonRepresentation representation = null;

		// Récupération de la liste des messages
		Representation repr = serv.getResource("intervention/" + interId
				+ "/message", null);

		try {
			representation = new JsonRepresentation(repr);

			JSONArray ar = representation.getJsonArray(); // Récupération de la
															// liste des
															// messages

			for (int i = 0; i < ar.length(); i++) {
				messages.add(new Message(ar.getJSONObject(i)));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return messages;
	}

	public void putMessage(Message msg) throws JSONException,
			BadResponseException {

		Representation r = new JsonRepresentation(msg.toJSON());

		serv.putResource("intervention/" + interId + "/message",
				msg.getUniqueID(), r);

	}

	public void postMessage(Message msg) throws JSONException,
			BadResponseException {

		Representation r = new JsonRepresentation(msg.toJSON());

		serv.postResource("intervention/" + interId + "/message",
				msg.getUniqueID(), r);

	}

	public void deleteMessage(Message msg) throws BadResponseException {
		serv.deleteResource("intervention/" + interId + "/message",
				msg.getUniqueID());
	}

	public Vehicule getVehicule(String vId) throws BadResponseException {
		Vehicule v = null;
		JsonRepresentation representation = null;

		Representation repr = serv.getResource("intervention/" + interId
				+ "/vehicule", vId);

		try {
			representation = new JsonRepresentation(repr);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			v = new Vehicule(representation.getJsonObject());
		} catch (InvalidJSONException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return v;
	}

	public List<Vehicule> getVehicules() throws BadResponseException {

		List<Vehicule> vehicules = new ArrayList<Vehicule>();
		JsonRepresentation representation = null;

		// Récupération de la liste des Vehicules
		Representation repr = serv.getResource("intervention/" + interId
				+ "/vehicule", null);

		try {
			representation = new JsonRepresentation(repr);

			JSONArray ar = representation.getJsonArray(); // Récupération de la
															// liste des
															// vehicules

			for (int i = 0; i < ar.length(); i++) {
				vehicules.add(new Vehicule(ar.getJSONObject(i)));
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}

		return vehicules;
	}

	public void putVehicule(Vehicule v) throws BadResponseException {

		Representation r;
		try {
			r = new JsonRepresentation(v.toJSON());
			serv.putResource("intervention/" + interId + "/vehicule", v.getUniqueID(), r);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		

	}

	public void deleteVehicule(Vehicule v) throws BadResponseException {
		serv.deleteResource("intervention/" + interId + "/vehicule",
				v.getUniqueID());
	}

	public Source getSource(String sId) throws BadResponseException {
		Source s = null;
		JsonRepresentation representation = null;

		Representation repr = serv.getResource("intervention/" + interId
				+ "/source", sId);

		try {
			representation = new JsonRepresentation(repr);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			s = new Source(representation.getJsonObject());
		} catch (InvalidJSONException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return s;
	}

	public void putSource(Source s) throws JSONException, BadResponseException {

		Representation r = new JsonRepresentation(s.toJSON());

		serv.putResource("intervention/" + interId + "/source",
				s.getUniqueID(), r);

	}

	public void deleteSource(Source s) throws BadResponseException {
		serv.deleteResource("intervention/" + interId + "/source",
				s.getUniqueID());
	}

	public List<Source> getSources() throws BadResponseException {

		List<Source> sources = new ArrayList<Source>();
		JsonRepresentation representation = null;

		// Récupération de la liste des messages
		Representation repr = serv.getResource("intervention/" + interId
				+ "/source", null);

		try {
			representation = new JsonRepresentation(repr);

			JSONArray ar = representation.getJsonArray(); // Récupération de la
															// liste des
															// messages
			// System.out.println(ar.toString());

			for (int i = 0; i < ar.length(); i++) {
				Source src = new Source(ar.getJSONObject(i));
				// System.out.println(src.getUniqueID());
				sources.add(src);
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}

		return sources;
	}

	public Cible getCible(String cId) throws InvalidJSONException,
			BadResponseException {
		Cible c = null;
		JsonRepresentation representation = null;

		Representation repr = serv.getResource("intervention/" + interId
				+ "/cible", cId);

		try {
			representation = new JsonRepresentation(repr);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			c = new Cible(representation.getJsonObject());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return c;
	}

	public void putCible(Cible c) throws JSONException, BadResponseException {
		Representation r = new JsonRepresentation(c.toJSON());
		serv.putResource("intervention/" + interId + "/cible", c.getUniqueID(),
				r);
	}

	public void deleteCible(Cible c) throws BadResponseException {
		serv.deleteResource("intervention/" + interId + "/cible",
				c.getUniqueID());
	}

	public Action getAction(String aId) throws InvalidJSONException,
			BadResponseException {
		Action a = null;
		JsonRepresentation representation = null;

		Representation repr = serv.getResource("intervention/" + interId
				+ "/action", aId);

		try {
			representation = new JsonRepresentation(repr);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			a = new Action(representation.getJsonObject());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return a;
	}

	public void putAction(Action a) throws JSONException, BadResponseException {
		Representation r = new JsonRepresentation(a.toJSON());
		serv.putResource("intervention/" + interId + "/cible", a.getUniqueID(),
				r);
	}

	public void deleteAction(Action a) throws BadResponseException {
		serv.deleteResource("intervention/" + interId + "/cible",
				a.getUniqueID());
	}

	public Implique getImplique(String iId) throws BadResponseException,
			InvalidJSONException {
		Implique i = null;
		JsonRepresentation representation = null;

		Representation repr = serv.getResource("intervention/" + interId
				+ "/implique", iId);

		try {
			representation = new JsonRepresentation(repr);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			i = new Implique(representation.getJsonObject());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return i;
	}

	public void putImplique(Implique i) throws JSONException,
			BadResponseException {
		Representation r = new JsonRepresentation(i.toJSON());
		serv.putResource("intervention/" + interId + "/implique",
				i.getUniqueID(), r);
	}

	public void deleteImplique(Implique i) throws BadResponseException {
		serv.deleteResource("intervention/" + interId + "/implique",
				i.getUniqueID());
	}

	@Override
	public void postVehicule(Vehicule v) throws JSONException,
			BadResponseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSource(Source s) throws JSONException, BadResponseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Cible> getCibles() throws BadResponseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postCible(Cible c) throws JSONException, BadResponseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Action> getActions() throws BadResponseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postAction(Action c) throws JSONException, BadResponseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Implique> getImpliques() throws BadResponseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postImplique(Implique c) throws JSONException,
			BadResponseException {
		// TODO Auto-generated method stub
		
	}
}
