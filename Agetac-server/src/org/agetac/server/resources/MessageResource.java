package org.agetac.server.resources;

import java.util.List;

import org.agetac.common.Intervention;
import org.agetac.common.Message;
import org.agetac.server.db.Interventions;
import org.agetac.server.db.Messages;
import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

public class MessageResource extends ServerResource {
	/**
	 * Retourne l'instance de l'message demander dans l'url
	 * 
	 * @return La representation de l'message demander ou
	 *         CLIENT_ERROR_NOT_ACCEPTABLE si l'id unique n'�xiste pas
	 * @throws Exception
	 *             En cas de probl�me de g�n�ration de repr�sentation. Ne
	 *             devrait pas arriver en pratique mais si c'est le cas, Restlet
	 *             met le bon code status.
	 */
	@Get
	public Representation getMessage() throws Exception {
		// Cr�e une representation JSON vide
		JsonRepresentation result = null;
		// R�cup�re l'identifiant unique de la ressource demand�e.
		String interId = (String) this.getRequestAttributes().get("interId");
		String msgId = (String) this.getRequestAttributes().get("messageId");
		System.out.println(msgId);
		// R�cup�ration des messages de l'intervention
		List<Message> messages = Interventions.getInstance().getIntervention(interId).getMessages();

		Message message = null;
		
		// Si on demande un message pr�cis
		if (msgId != null) {
			// Recherche du message demand�
			for (int i = 0; i < messages.size(); i++) {
				if (messages.get(i).getUniqueID().equals(msgId)) {
					message = messages.get(i);
				}
			}
			// Si le message n'est pas trouv�
			if (message == null) {
				result = null;
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			} else {
				result = new JsonRepresentation(message.toJson());
			}
		// Si on veut tous les messages
		} else if (msgId == null) {
			
			/*JSONObject jsonOb = new JSONObject(); // JsonObject englobant
			JSONArray ar = new JSONArray(); // JsonArray contenant la liste des messages
			
			for(int i=0; i< messages.size();i++){
				ar.put(new JSONObject(messages.get(i).toJson())); // On ajoute la un jsonObject par message dans le jsonArray
			}
			jsonOb.put("messages", ar); // On met le JsonArray dans le JsonObject englobant*/
			
			JSONArray jsonAr = new JSONArray();
			for(int i=0; i< messages.size();i++){
				jsonAr.put(new JSONObject(messages.get(i).toJson())); // On ajoute la un jsonObject par message dans le jsonArray
			}
			
			result = new JsonRepresentation(jsonAr); // On cr�e la repr�sentation de l'objet
		}

		// Retourne la repr�sentation, le code status indique au client si elle
		// est valide
		return result;
	}

	/**
	 * Ajoute le message transmit � la base de donn�es interne.
	 * 
	 * @param representation
	 *            La repr�sentation Json de l'message
	 * @return null.
	 * @throws Exception
	 *             En cas de probl�me de lecture de la representation.
	 */
	@Put
	public Representation putMessage(Representation representation)
			throws Exception {
		// R�cup�re l'identifiant unique de la ressource demand�e.
		String interId = (String) this.getRequestAttributes().get("interId");
		String msgId = (String) this.getRequestAttributes().get("messageId");

		// R�cup�re la repr�sentation JSON du message
		JsonRepresentation jsonRepr = new JsonRepresentation(representation);
		// System.out.println("JsonRepresentation : " + jsonRepr.getText());

		// Transforme la representation en objet java
		JSONObject jsObj = jsonRepr.getJsonObject();
		Message message = new Message(jsObj);
		// System.out.println("Message : " + message.toJson());

		// Ajoute l'message a la base de donn�e
		Intervention i = Interventions.getInstance().getIntervention(interId);
		List<Message> lm = i.getMessages();
		lm.add(message);
		// Messages.getInstance().addMessage(message);
		// Pas besoin de retourner de repr�sentation au client
		return null;
	}

	/**
	 * Supprime le message identifi� de la base de donn�es
	 * 
	 * @return null.
	 */
	@Delete
	public Representation deleteMessage() {
		// R�cup�re l'id dans l'url
		String uniqueID = (String) this.getRequestAttributes().get("uniqueID");
		// On s'assure qu'il n'est plus pr�sent en base de donn�es
		Messages.getInstance().deleteMessage(uniqueID);
		return null;
	}
}