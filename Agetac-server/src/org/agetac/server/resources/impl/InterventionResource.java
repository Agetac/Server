package org.agetac.server.resources.impl;

import java.util.List;

import org.agetac.common.model.impl.Intervention;
import org.agetac.server.db.Interventions;
import org.agetac.server.resources.sign.IServerResource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

public class InterventionResource extends ServerResource implements	IServerResource {

	@Override
	public Representation getResource() throws Exception {
		// Cr�e une representation JSON vide
		JsonRepresentation result = null;
		// R�cup�re l'identifiant unique de la ressource demand�e.
		String uniqueID = (String) this.getRequestAttributes().get("interId");

		if (uniqueID != null) {

			Intervention intervention = null;

			// La recherche dans la base de donn�es.
			intervention = Interventions.getInstance()
					.getIntervention(uniqueID);
			if (intervention == null) {
				// Ressource non-trouv�, envois du code status 406
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			} else {
				// Ressource trouv�, envoie de la repr�sentation Json
				result = new JsonRepresentation(intervention.toJSON());
				// Le code status par d�faut est 200 s'il n'est pas
			}

			// Si on veut toutes les interventions
		} else if (uniqueID == null) {

			List<Intervention> interventions = Interventions.getInstance().getInterventions();
			
			// Cr�ation d'une liste Json
			JSONArray jsonAr = new JSONArray(); 
			for (int i = 0; i < interventions.size(); i++) {
				// On ajoute un jsonObject contenant le implique dans le jsonArray
				jsonAr.put(interventions.get(i).toJSON());
			}
			// On cr�e la repr�sentation de la liste
			result = new JsonRepresentation(jsonAr);

		}

		// Retourne la repr�sentation, le code status indique au client si elle
		// est valide
		return result;
	}

	@Override
	public Representation putResource(Representation representation)
			throws Exception {
		
		String interId = (String) this.getRequestAttributes().get("interId");
		
		List<Intervention> li = Interventions.getInstance().getInterventions();

		Intervention intervention;
		JsonRepresentation jsonRepr;

		// Si l'id est egal � "new" on cr�e un nouvel objet
		if (interId.equals("new")) {

			// Nouvel ID
			String uid = (li.size() + 1) + "";
			intervention = new Intervention(uid);
			jsonRepr = new JsonRepresentation(intervention.toJSON());

		} else {

			// R�cup�re la repr�sentation JSON de la ressource
			jsonRepr = new JsonRepresentation(representation);
			// Transforme la representation en objet java
			JSONObject jsObj = jsonRepr.getJsonObject();
			intervention = new Intervention(jsObj);
			
			// On v�rifie si l'intervention n'�xiste pas d�j�
			if (Interventions.getInstance().getIntervention(intervention.getUniqueID()) != null) {
				// Ressource non-trouv�, envois du code status 406
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
				return null;
			}
		}
		
		// Ajoute l'intervention a la base de donn�e
		Interventions.getInstance().addIntervention(intervention);

		// On retourne la repr�sentation au client
		return jsonRepr;
	}

	@Override
	public Representation deleteResource() {
		// R�cup�re l'id dans l'url
		String uniqueID = (String) this.getRequestAttributes().get("interId");
		// On s'assure qu'il n'est plus pr�sent en base de donn�es
		Interventions.getInstance().deleteIntervention(uniqueID);
		return null;
	}

	@Override
	public Representation postResource(Representation representation)
			throws Exception {
		// R�cup�re l'identifiant unique de la rescible demand�e.
		String interId = (String) this.getRequestAttributes().get("interId");

		// R�cup�ration des messages de l'intervention
		Interventions interventions = Interventions.getInstance();

		Intervention intervention = null;

		// Si on demande un message pr�cis
		if (interId != null) {
			// Si le message n'est pas trouv�
			if (interventions.getIntervention(interId) == null) {
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			} else {
				// R�cup�re la repr�sentation JSON de l'message a mettre a jour
				JsonRepresentation jsonRepr = new JsonRepresentation(
						representation);

				// Transforme la representation en objet json
				JSONObject jsObj = jsonRepr.getJsonObject();

				// Transforme en Message
				intervention = new Intervention(jsObj);

				// Mise a jour de l'message
				Interventions.getInstance().addIntervention(intervention);
			}
		} else {
			// Pas d'id -> Erreur
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
		}

		// Pas besoin de retourner de repr�sentation au client
		return null;
	}
}
