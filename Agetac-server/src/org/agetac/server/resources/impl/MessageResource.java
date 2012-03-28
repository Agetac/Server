package org.agetac.server.resources.impl;

import java.util.List;

import org.agetac.common.model.impl.Intervention;
import org.agetac.common.model.impl.Message;
import org.agetac.server.db.Interventions;
import org.agetac.server.resources.sign.IServerResource;
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

	@Get
	public Representation getResource() throws Exception {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
		// Crée une representation JSON vide
		JsonRepresentation result = null;
		// Récupère l'identifiant unique de la ressource demandée.
		String interId = (String) this.getRequestAttributes().get("interId");
		String msgId = (String) this.getRequestAttributes().get("messageId");
		
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
				result = new JsonRepresentation(message.toJSON());
			}
		// Si on veut tous les messages
		} else if (msgId == null) {
			
			JSONArray jsonAr = new JSONArray(); //Création d'une liste Json
			for(int i=0; i< messages.size();i++){
				jsonAr.put(messages.get(i).toJSON()); // On ajoute un jsonObject contenant le message dans le jsonArray
			}
			
			result = new JsonRepresentation(jsonAr); // On crée la représentation de la liste
		}

		// Retourne la représentation, le code status indique au client si elle est valide
		return result;
	}


	@Put
	public Representation putResource(Representation representation)
			throws Exception {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
		// Récupère l'identifiant unique de la ressource demandée.
		String interId = (String) this.getRequestAttributes().get("interId");

		Intervention i = Interventions.getInstance().getIntervention(interId);
		List<Message> lm = i.getMessages();

		JsonRepresentation jsonRepr = new JsonRepresentation(representation);
		Message message = new Message(jsonRepr.getJsonObject());
		
	
		// Nouvel ID
		String uid = (lm.size() + 1) + "";
		message.setUniqueID(uid);
		
		//Ajout du message
		lm.add(message);
		
		
		jsonRepr = new JsonRepresentation(message.toJSON());
		
		// On retourne la représentation au client
		return jsonRepr;
	}

	@Delete
	public Representation deleteResource() {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
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

	@Override
	public Representation postResource(Representation representation)
			throws Exception {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
		// Récupère l'identifiant unique de la ressource demandée.
		String interId = (String) this.getRequestAttributes().get("interId");
		String msgId = (String) this.getRequestAttributes().get("messageId");
		
		// Récupération des messages de l'intervention
		List<Message> messages = Interventions.getInstance().getIntervention(interId).getMessages();
		
		Message message = null;
		JsonRepresentation jsonRepr = null;
		// Si on demande un message précis
		if (msgId != null) {
		
			// Recherche de l'message demandée
			for (int i = 0; i < messages.size(); i++) {
				if (messages.get(i).getUniqueID().equals(msgId)) {
					
					// Récupère la représentation JSON de l'message a mettre a jour
					 jsonRepr = new JsonRepresentation(representation);

					// Transforme la representation en objet json
					JSONObject jsObj = jsonRepr.getJsonObject();
					
					// Transforme en Message
					message = new Message(jsObj);
					
					// Mise a jour de l'message
					messages.set(i, message);
					break;
					
				}
			}
			
			// Si le message n'est pas trouvé
			if (message == null) {
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			}
			
			
		}else{
			// Pas d'id -> Erreur
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
		}
		jsonRepr = new JsonRepresentation(message.toJSON());
		// Pas besoin de retourner de représentation au client
		return jsonRepr;
	}


}
