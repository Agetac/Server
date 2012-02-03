package org.agetac.server.resources;

import java.util.List;

import org.agetac.model.impl.Action;
import org.agetac.model.impl.Intervention;
import org.agetac.server.db.Interventions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

public class ActionResource extends ServerResource implements IServerResource {

	@Override
	public Representation getResource() throws Exception {
		// Crée une representation JSON vide
		JsonRepresentation result = null;
		// Récupère l'identifiant unique de la ressource demandée.
		String interId = (String) this.getRequestAttributes().get("interId");
		String actId = (String) this.getRequestAttributes().get("actionId");
		System.out.println(actId);
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
			// Si le action n'est pas trouvé
			if (action == null) {
				result = null;
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			} else {
				result = new JsonRepresentation(action.toJson());
			}
		// Si on veut tous les actions
		} else if (actId == null) {
			
			JSONArray jsonAr = new JSONArray(); //Création d'une liste Json
			for(int i=0; i< actions.size();i++){
				jsonAr.put(actions.get(i).toJson()); // On ajoute un jsonObject contenant le action dans le jsonArray
			}
			
			result = new JsonRepresentation(jsonAr); // On crée la représentation de la liste
		}

		// Retourne la représentation, le code status indique au client si elle est valide
		return result;
	}

	@Override
	public Representation putResource(Representation representation)
			throws Exception {
		// Récupère l'identifiant unique de la ressource demandée.
		String interId = (String) this.getRequestAttributes().get("interId");

		// Récupère la représentation JSON du action
		JsonRepresentation jsonRepr = new JsonRepresentation(representation);
		// System.out.println("JsonRepresentation : " + jsonRepr.getText());

		// Transforme la representation en objet java
		JSONObject jsObj = jsonRepr.getJsonObject();
		Action action = new Action(jsObj);
		// System.out.println("Action : " + action.toJson());

		// Ajoute l'action a la base de donnée
		Intervention i = Interventions.getInstance().getIntervention(interId);
		List<Action> la = i.getActions();
		la.add(action);
		// Actions.getInstance().addAction(action);
		// Pas besoin de retourner de représentation au client
		return null;
	}

	@Override
	public Representation deleteResource() {
		// Récupère l'id dans l'url
		String interId = (String) this.getRequestAttributes().get("interId");
		String actId = (String) this.getRequestAttributes().get("actId");
		
		
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

}
