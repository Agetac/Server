package org.agetac.server.resources;

import java.util.List;

import org.agetac.common.Action;
import org.agetac.common.Intervention;
import org.agetac.common.Action;
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
		// Cr�e une representation JSON vide
		JsonRepresentation result = null;
		// R�cup�re l'identifiant unique de la ressource demand�e.
		String interId = (String) this.getRequestAttributes().get("interId");
		String actId = (String) this.getRequestAttributes().get("actionId");
		System.out.println(actId);
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
			// Si le action n'est pas trouv�
			if (action == null) {
				result = null;
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			} else {
				result = new JsonRepresentation(action.toJson());
			}
		// Si on veut tous les actions
		} else if (actId == null) {
			
			JSONArray jsonAr = new JSONArray(); //Cr�ation d'une liste Json
			for(int i=0; i< actions.size();i++){
				jsonAr.put(new JSONObject(actions.get(i).toJson())); // On ajoute un jsonObject contenant le action dans le jsonArray
			}
			
			result = new JsonRepresentation(jsonAr); // On cr�e la repr�sentation de la liste
		}

		// Retourne la repr�sentation, le code status indique au client si elle est valide
		return result;
	}

	@Override
	public Representation putResource(Representation representation)
			throws Exception {
		// R�cup�re l'identifiant unique de la ressource demand�e.
		String interId = (String) this.getRequestAttributes().get("interId");

		// R�cup�re la repr�sentation JSON du action
		JsonRepresentation jsonRepr = new JsonRepresentation(representation);
		// System.out.println("JsonRepresentation : " + jsonRepr.getText());

		// Transforme la representation en objet java
		JSONObject jsObj = jsonRepr.getJsonObject();
		Action action = new Action(jsObj);
		// System.out.println("Action : " + action.toJson());

		// Ajoute l'action a la base de donn�e
		Intervention i = Interventions.getInstance().getIntervention(interId);
		List<Action> la = i.getActions();
		la.add(action);
		// Actions.getInstance().addAction(action);
		// Pas besoin de retourner de repr�sentation au client
		return null;
	}

	@Override
	public Representation deleteResource() {
		// R�cup�re l'id dans l'url
		String interId = (String) this.getRequestAttributes().get("interId");
		String actId = (String) this.getRequestAttributes().get("actId");
		
		
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

}