package org.agetac.server.resources.impl;

import java.util.List;

import org.agetac.common.model.impl.Action;
import org.agetac.common.model.impl.Intervention;
import org.agetac.common.model.impl.Position;
import org.agetac.common.model.impl.Action.ActionType;
import org.agetac.server.db.Interventions;
import org.agetac.server.resources.sign.IServerResource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

public class ActionResource extends ServerResource implements IServerResource {

	@Override
	public Representation getResource() throws Exception {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
		// Crée une representation JSON vide
		JsonRepresentation result = null;
		
		// Récupère l'identifiant unique de la ressource demandée.
		String interId = (String) this.getRequestAttributes().get("interId");
		String actId = (String) this.getRequestAttributes().get("actionId");
		
		// Récupération des actions de l'intervention
		List<Action> actions = Interventions.getInstance().getIntervention(interId).getActions();

		Action action = null;
		
		// Si on demande un action précis
		if (actId != null) {
			// Recherche du action demandé
			for (int i = 0; i < actions.size(); i++) {
				if (actions.get(i).getUniqueID().equals(actId)) {
					action = actions.get(i);
				}
			}
			// Si l'action n'est pas trouvé
			if (action == null) {
				result = null;
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			} else {
				result = new JsonRepresentation(action.toJSON());
			}
		// Si on veut toutes les actions
		} else if (actId == null) {
			
			JSONArray jsonAr = new JSONArray(); //Création d'une liste Json
			for(int i=0; i< actions.size();i++){
				jsonAr.put(actions.get(i).toJSON()); // On ajoute un jsonObject contenant le action dans le jsonArray
			}
			
			result = new JsonRepresentation(jsonAr); // On crée la représentation de la liste
		}

		// Retourne la représentation, le code status indique au client si elle est valide
		return result;
	}

	@Override
	public Representation putResource(Representation representation)
			throws Exception {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
		// Récupère l'identifiant unique de la ressource demandée.
		String interId = (String) this.getRequestAttributes().get("interId");
		
		Intervention i = Interventions.getInstance().getIntervention(interId);
		List<Action> la = i.getActions();

		
		// Récupère la représentation JSON du action
		JsonRepresentation jsonRepr = new JsonRepresentation(representation);
		Action action = new Action(jsonRepr.getJsonObject());
		
		// Nouvel ID
		String uid = (la.size() + 1) + "";
		action.setUniqueID(uid);
		
		//Ajout du action
		la.add(action);

		// On retourne la nouvelle représentation au client
		jsonRepr = new JsonRepresentation(action.toJSON());
		
		// On retourne la représentation au client
		return jsonRepr;
		
	}
	
	@Override
	public Representation deleteResource() {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
		// Récupère l'id dans l'url
		String interId = (String) this.getRequestAttributes().get("interId");
		String actId = (String) this.getRequestAttributes().get("actionId");
		
		
		// On s'assure qu'il n'est plus présent en base de données
		
		Intervention inter = Interventions.getInstance().getIntervention(interId);
		List<Action> actions = inter.getActions();
		for (int i = 0; i < actions.size(); i++) {
			if (actions.get(i).getUniqueID().equals(actId)) {
				actions.remove(actions.get(i));
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
		String actId = (String) this.getRequestAttributes().get("actionId");
		
		// Récupération des actions de l'intervention
		List<Action> actions = Interventions.getInstance().getIntervention(interId).getActions();
		
		Action action = null;
		JsonRepresentation jsonRepr = null ;
		// Si on demande un action précis
		if (actId != null) {
		
			// Recherche de l'action demandée
			for (int i = 0; i < actions.size(); i++) {
				if (actions.get(i).getUniqueID().equals(actId)) {
					
					// Récupère la représentation JSON de l'action a mettre a jour
					jsonRepr = new JsonRepresentation(representation);

					// Transforme la representation en objet json
					JSONObject jsObj = jsonRepr.getJsonObject();
					// Transforme en Action
					action = new Action(jsObj);
					
					// Mise a jour de l'action
					actions.set(i, action);
					break;
					
				}
			}
			
			// Si le action n'est pas trouvé
			if (action == null) {
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			}
			
			
		}else{
			// Pas d'id -> Erreur
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
		}
		jsonRepr = new JsonRepresentation(action.toJSON());
		// Pas besoin de retourner de représentation au client
		return jsonRepr;
	}


}
