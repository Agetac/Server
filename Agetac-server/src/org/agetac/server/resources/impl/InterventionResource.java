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
		// Crée une representation JSON vide
		JsonRepresentation result = null;
		// Récupère l'identifiant unique de la ressource demandée.
		String uniqueID = (String) this.getRequestAttributes().get("interId");

		if (uniqueID != null) {

			Intervention intervention = null;

			// La recherche dans la base de données.
			intervention = Interventions.getInstance()
					.getIntervention(uniqueID);
			if (intervention == null) {
				// Ressource non-trouvé, envois du code status 406
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			} else {
				// Ressource trouvé, envoie de la représentation Json
				result = new JsonRepresentation(intervention.toJSON());
				// Le code status par défaut est 200 s'il n'est pas
			}

			// Si on veut toutes les interventions
		} else if (uniqueID == null) {

			List<Intervention> interventions = Interventions.getInstance().getInterventions();
			
			// Création d'une liste Json
			JSONArray jsonAr = new JSONArray(); 
			for (int i = 0; i < interventions.size(); i++) {
				// On ajoute un jsonObject contenant le implique dans le jsonArray
				jsonAr.put(interventions.get(i).toJSON());
			}
			// On crée la représentation de la liste
			result = new JsonRepresentation(jsonAr);

		}

		// Retourne la représentation, le code status indique au client si elle
		// est valide
		return result;
	}

	@Override
	public Representation putResource(Representation representation)
			throws Exception {
		
		List<Intervention> li = Interventions.getInstance().getInterventions();

		Intervention intervention;
		JsonRepresentation jsonRepr = new JsonRepresentation(representation);
		
		// On récupère l'intervention transmise par le client
		intervention = new Intervention(jsonRepr.getJsonObject());

		// Génération d'un nouvel uid
		String uid = (li.size() + 1) + "";
		
		// Mise a jour de l'intervention avec son uid
		intervention.setUniqueID(uid);
		jsonRepr = new JsonRepresentation(intervention.toJSON());
		
		// Ajoute l'intervention a la base de donnée
		Interventions.getInstance().addIntervention(intervention);

		// On retourne la nouvelle représentation au client
		return jsonRepr;
	}

	@Override
	public Representation deleteResource() {
		// Récupère l'id dans l'url
		String uniqueID = (String) this.getRequestAttributes().get("interId");
		// On s'assure qu'il n'est plus présent en base de données
		Interventions.getInstance().deleteIntervention(uniqueID);
		return null;
	}

	@Override
	public Representation postResource(Representation representation)
			throws Exception {
		// Récupère l'identifiant unique de la rescible demandée.
		String interId = (String) this.getRequestAttributes().get("interId");

		// Récupération des messages de l'intervention
		Interventions interventions = Interventions.getInstance();

		Intervention intervention = null;
		JsonRepresentation jsonRepr = null;
		// Si on demande un message précis
		if (interId != null) {
			// Si le message n'est pas trouvé
			if (interventions.getIntervention(interId) == null) {
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			} else {
				// Récupère la représentation JSON de l'message a mettre a jour
				 jsonRepr = new JsonRepresentation(
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

		jsonRepr = new JsonRepresentation(intervention.toJSON());
		// Pas besoin de retourner de représentation au client
		return jsonRepr;
	}
}
