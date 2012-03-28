package org.agetac.server.resources.impl;

import java.util.List;

import org.agetac.common.model.impl.Cible;
import org.agetac.common.model.impl.Intervention;
import org.agetac.common.model.impl.Position;
import org.agetac.server.db.Interventions;
import org.agetac.server.resources.sign.IServerResource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

public class CibleResource extends ServerResource implements IServerResource {

	@Override
	public Representation getResource() throws Exception {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
		// Crée une representation JSON vide
		JsonRepresentation result = null;
		// Récupère l'identifiant unique de la rescible demandée.
		String interId = (String) this.getRequestAttributes().get("interId");
		String cibId = (String) this.getRequestAttributes().get("cibleId");
		System.out.println(cibId);
		// Récupération des cibles de l'intervention
		List<Cible> cibles = Interventions.getInstance()
				.getIntervention(interId).getCibles();

		Cible cible = null;

		// Si on demande un cible précis
		if (cibId != null) {
			// Recherche du cible demandé
			for (int i = 0; i < cibles.size(); i++) {
				if (cibles.get(i).getUniqueID().equals(cibId)) {
					cible = cibles.get(i);
				}
			}
			// Si le cible n'est pas trouvé
			if (cible == null) {
				result = null;
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			} else {
				result = new JsonRepresentation(cible.toJSON());
			}
			// Si on veut tous les cibles
		} else if (cibId == null) {

			JSONArray jsonAr = new JSONArray(); // Création d'une liste Json
			for (int i = 0; i < cibles.size(); i++) {
				jsonAr.put(cibles.get(i).toJSON()); // On ajoute un jsonObject
													// contenant le cible dans
													// le jsonArray
			}

			result = new JsonRepresentation(jsonAr); // On crée la
														// représentation de la
														// liste
		}

		// Retourne la représentation, le code status indique au client si elle
		// est valide
		return result;
	}

	@Override
	public Representation putResource(Representation representation)
			throws Exception {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
		// Récupère l'identifiant unique de la rescible demandée.
		String interId = (String) this.getRequestAttributes().get("interId");

		Intervention i = Interventions.getInstance().getIntervention(interId);
		List<Cible> lm = i.getCibles();

		JsonRepresentation jsonRepr = new JsonRepresentation(representation);
		Cible cible = new Cible (jsonRepr.getJsonObject());

		// Nouvel ID
		String uid = (lm.size() + 1) + "";
		
		cible.setUniqueID(uid);
		
		lm.add(cible);
		
		jsonRepr = new JsonRepresentation(cible.toJSON());
		
		// On retourne la représentation au client
		return jsonRepr;
	}

	@Override
	public Representation deleteResource() {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
		// Récupère l'id dans l'url
		String interId = (String) this.getRequestAttributes().get("interId");
		String cibId = (String) this.getRequestAttributes().get("cibleId");

		// On s'assure qu'il n'est plus présent en base de données

		Intervention inter = Interventions.getInstance().getIntervention(
				interId);
		List<Cible> cibles = inter.getCibles();
		for (int i = 0; i < cibles.size(); i++) {
			if (cibles.get(i).getUniqueID().equals(cibId)) {
				cibles.remove(cibles.get(i));
			}
		}

		return null;
	}

	@Override
	public Representation postResource(Representation representation) throws Exception {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
		// Récupère l'identifiant unique de la rescible demandée.
		String interId = (String) this.getRequestAttributes().get("interId");
		String cibleId = (String) this.getRequestAttributes().get("cibleId");

		// Récupération des messages de l'intervention
		List<Cible> cibles = Interventions.getInstance().getIntervention(interId).getCibles();

		Cible cible = null;
		JsonRepresentation jsonRepr = null;
		// Si on demande un message précis
		if (cibleId != null) {

			// Recherche de l'message demandée
			for (int i = 0; i < cibles.size(); i++) {
				if (cibles.get(i).getUniqueID().equals(cibleId)) {

					// Récupère la représentation JSON de l'message a mettre a
					// jour
					 jsonRepr = new JsonRepresentation(	representation);

					// Transforme la representation en objet json
					JSONObject jsObj = jsonRepr.getJsonObject();

					// Transforme en Message
					cible = new Cible(jsObj);

					// Mise a jour de l'message
					cibles.set(i, cible);
					break;

				}
			}

			// Si le message n'est pas trouvé
			if (cible == null) {
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			}

		} else {
			// Pas d'id -> Erreur
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
		}
		
		jsonRepr = new JsonRepresentation(cible.toJSON());
		// Pas besoin de retourner de représentation au client
		return jsonRepr;
	}

}
