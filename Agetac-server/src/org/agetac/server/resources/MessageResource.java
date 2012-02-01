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

public class MessageResource extends ServerResource implements IServerResource{
	/**
	 * Retourne l'instance de du message demander dans l'url
	 * 
	 * @return La representation de l'message demander ou
	 *         CLIENT_ERROR_NOT_ACCEPTABLE si l'id unique n'éxiste pas
	 * @throws Exception
	 *             En cas de problème de génération de représentation. Ne
	 *             devrait pas arriver en pratique mais si c'est le cas, Restlet
	 *             met le bon code status.
	 */
	@Get
	public Representation getResource() throws Exception {
		// Crée une representation JSON vide
		JsonRepresentation result = null;
		// Récupère l'identifiant unique de la ressource demandée.
		String interId = (String) this.getRequestAttributes().get("interId");
		String msgId = (String) this.getRequestAttributes().get("messageId");
		System.out.println(msgId);
		// Récupération des messages de l'intervention
		List<Message> messages = Interventions.getInstance().getIntervention(interId).getMessages();

		Message message = null;
		
		// Si on demande un message précis
		if (msgId != null) {
			// Recherche du message demandé
			for (int i = 0; i < messages.size(); i++) {
				if (messages.get(i).getUniqueID().equals(msgId)) {
					message = messages.get(i);
				}
			}
			// Si le message n'est pas trouvé
			if (message == null) {
				result = null;
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			} else {
				result = new JsonRepresentation(message.toJson());
			}
		// Si on veut tous les messages
		} else if (msgId == null) {
			
			JSONArray jsonAr = new JSONArray(); //Création d'une liste Json
			for(int i=0; i< messages.size();i++){
				jsonAr.put(new JSONObject(messages.get(i).toJson())); // On ajoute un jsonObject contenant le message dans le jsonArray
			}
			
			result = new JsonRepresentation(jsonAr); // On crée la représentation de la liste
		}

		// Retourne la représentation, le code status indique au client si elle est valide
		return result;
	}

	/**
	 * Ajoute le message transmit à la base de données interne.
	 * 
	 * @param representation
	 *            La représentation Json de l'message
	 * @return null.
	 * @throws Exception
	 *             En cas de problème de lecture de la representation.
	 */
	@Put
	public Representation putResource(Representation representation)
			throws Exception {
		// Récupère l'identifiant unique de la ressource demandée.
		String interId = (String) this.getRequestAttributes().get("interId");

		// Récupère la représentation JSON du message
		JsonRepresentation jsonRepr = new JsonRepresentation(representation);
		// System.out.println("JsonRepresentation : " + jsonRepr.getText());

		// Transforme la representation en objet java
		JSONObject jsObj = jsonRepr.getJsonObject();
		Message message = new Message(jsObj);
		// System.out.println("Message : " + message.toJson());

		// Ajoute l'message a la base de donnée
		Intervention i = Interventions.getInstance().getIntervention(interId);
		List<Message> lm = i.getMessages();
		lm.add(message);
		// Messages.getInstance().addMessage(message);
		// Pas besoin de retourner de représentation au client
		return null;
	}

	/**
	 * Supprime le message identifié de la base de données
	 * 
	 * @return null.
	 */
	@Delete
	public Representation deleteResource() {
		// Récupère l'id dans l'url
		String interId = (String) this.getRequestAttributes().get("interId");
		String msgId = (String) this.getRequestAttributes().get("messageId");
		
		
		// On s'assure qu'il n'est plus présent en base de données
		
		Intervention inter = Interventions.getInstance().getIntervention(interId);
		List<Message> messages = inter.getMessages();
		for (int i = 0; i < messages.size(); i++) {
			if (messages.get(i).getUniqueID().equals(msgId)) {
				messages.remove(messages.get(i));
			}
		}
		
		return null;
	}


}
