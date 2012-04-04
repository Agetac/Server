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
		
		// Cr�e une representation JSON vide
		JsonRepresentation result = null;
		
		// R�cup�re l'identifiant unique de la ressource demand�e.
		String interId = (String) this.getRequestAttributes().get("interId");
		String actId = (String) this.getRequestAttributes().get("actionId");
		
		// R�cup�ration des actions de l'intervention
		List<Action> actions = Interventions.getInstance().getIntervention(interId).getActions();

		Action action = null;
		
		// Si on demande un action pr�cis
		if (actId != null) {
			// Recherche du action demand�
			for (int i = 0; i < actions.size(); i++) {
				if (actions.get(i).getUniqueID().equals(actId)) {
					action = actions.get(i);
				}
			}
			// Si l'action n'est pas trouv�
			if (action == null) {
				result = null;
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			} else {
				result = new JsonRepresentation(action.toJSON());
			}
		// Si on veut toutes les actions
		} else if (actId == null) {
			
			JSONArray jsonAr = new JSONArray(); //Cr�ation d'une liste Json
			for(int i=0; i< actions.size();i++){
				jsonAr.put(actions.get(i).toJSON()); // On ajoute un jsonObject contenant le action dans le jsonArray
			}
			
			result = new JsonRepresentation(jsonAr); // On cr�e la repr�sentation de la liste
		}

		// Retourne la repr�sentation, le code status indique au client si elle est valide
		return result;
	}

	@Override
	public Representation putResource(Representation representation)
			throws Exception {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
		// R�cup�re l'identifiant unique de la ressource demand�e.
		String interId = (String) this.getRequestAttributes().get("interId");
		
		Intervention i = Interventions.getInstance().getIntervention(interId);
		List<Action> la = i.getActions();

		
		// R�cup�re la repr�sentation JSON du action
		JsonRepresentation jsonRepr = new JsonRepresentation(representation);
		Action action = new Action(jsonRepr.getJsonObject());
		
		// Nouvel ID
		String uid = (la.size() + 1) + "";
		action.setUniqueID(uid);
		
		//Ajout du action
		la.add(action);

		// On retourne la nouvelle repr�sentation au client
		jsonRepr = new JsonRepresentation(action.toJSON());
		
		// On retourne la repr�sentation au client
		return jsonRepr;
		
	}
	
	@Override
	public Representation deleteResource() {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
		// R�cup�re l'id dans l'url
		String interId = (String) this.getRequestAttributes().get("interId");
		String actId = (String) this.getRequestAttributes().get("actionId");
		
		
		// On s'assure qu'il n'est plus pr�sent en base de donn�es
		
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
		
		// R�cup�re l'identifiant unique de la ressource demand�e.
		String interId = (String) this.getRequestAttributes().get("interId");
		String actId = (String) this.getRequestAttributes().get("actionId");
		
		// R�cup�ration des actions de l'intervention
		List<Action> actions = Interventions.getInstance().getIntervention(interId).getActions();
		
		Action action = null;
		JsonRepresentation jsonRepr = null ;
		// Si on demande un action pr�cis
		if (actId != null) {
		
			// Recherche de l'action demand�e
			for (int i = 0; i < actions.size(); i++) {
				if (actions.get(i).getUniqueID().equals(actId)) {
					
					// R�cup�re la repr�sentation JSON de l'action a mettre a jour
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
			
			// Si le action n'est pas trouv�
			if (action == null) {
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			}
			
			
		}else{
			// Pas d'id -> Erreur
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
		}
		jsonRepr = new JsonRepresentation(action.toJSON());
		// Pas besoin de retourner de repr�sentation au client
		return jsonRepr;
	}


}
