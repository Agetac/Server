package org.agetac.server.resources.impl;

import java.util.List;

import org.agetac.common.model.impl.Implique;
import org.agetac.common.model.impl.Intervention;
import org.agetac.server.db.Interventions;
import org.agetac.server.resources.sign.IServerResource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

public class ImpliqueResource extends ServerResource implements IServerResource {

	@Override
	public Representation getResource() throws Exception {
		// Crée une representation JSON vide
		JsonRepresentation result = null;
		// Récupère l'identifiant unique de la resource demandée.
		String interId = (String) this.getRequestAttributes().get("interId");
		String impId = (String) this.getRequestAttributes().get("impliqueId");
	
		// Récupération des impliques de l'intervention
		List<Implique> impliques = Interventions.getInstance().getIntervention(interId).getImpliques();

		Implique implique = null;
		
		// Si on demande un implique précis
		if (impId != null) {
			// Recherche du implique demandé
			for (int i = 0; i < impliques.size(); i++) {
				if (impliques.get(i).getUniqueID().equals(impId)) {
					implique = impliques.get(i);
				}
			}
			// Si le implique n'est pas trouvé
			if (implique == null) {
				result = null;
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			} else {
				result = new JsonRepresentation(implique.toJSON());
			}
		// Si on veut tous les impliques
		} else if (impId == null) {
			
			JSONArray jsonAr = new JSONArray(); //Création d'une liste Json
			for(int i=0; i< impliques.size();i++){
				jsonAr.put(impliques.get(i).toJSON()); // On ajoute un jsonObject contenant le implique dans le jsonArray
			}
			
			result = new JsonRepresentation(jsonAr); // On crée la représentation de la liste
		}

		// Retourne la représentation, le code status indique au client si elle est valide
		return result;
	}

	@Override
	public Representation putResource(Representation representation)
			throws Exception {
		// Récupère l'identifiant unique de la resimplique demandée.
		String interId = (String) this.getRequestAttributes().get("interId");
		Intervention i = Interventions.getInstance().getIntervention(interId);
		List<Implique> lm = i.getImpliques();

		JsonRepresentation jsonRepr = new JsonRepresentation(representation);
		Implique implique = new Implique(jsonRepr.getJsonObject());
		
			
		// Nouvel ID
		String uid = (lm.size() + 1) + "";
		implique.setUniqueID(uid);
		
		lm.add(implique);
		
		jsonRepr = new JsonRepresentation(implique.toJSON());

		return jsonRepr;
	}

	@Override
	public Representation deleteResource() {
		// Récupère l'id dans l'url
		String interId = (String) this.getRequestAttributes().get("interId");
		String impId = (String) this.getRequestAttributes().get("impliqueId");
		
		// On s'assure qu'il n'est plus présent en base de données
	
		Intervention inter = Interventions.getInstance().getIntervention(interId);
		List<Implique> impliques = inter.getImpliques();
		for (int i = 0; i < impliques.size(); i++) {
			if (impliques.get(i).getUniqueID().equals(impId)) {
				impliques.remove(impliques.get(i));
			}
		}
		
		return null;
	}

	@Override
	public Representation postResource(Representation representation)
			throws Exception {
		// Récupère l'identifiant unique de la ressource demandée.
		String interId = (String) this.getRequestAttributes().get("interId");
		String impId = (String) this.getRequestAttributes().get("sourceId");

		// Récupération des messages de l'intervention
		List<Implique> impliques = Interventions.getInstance().getIntervention(interId).getImpliques();

		Implique implique = null;

		// Si on demande un message précis
		if (impId != null) {

			// Recherche de l'message demandée
			for (int i = 0; i < impliques.size(); i++) {
				if (impliques.get(i).getUniqueID().equals(impId)) {

					// Récupère la représentation JSON de l'message a mettre a
					// jour
					JsonRepresentation jsonRepr = new JsonRepresentation(
							representation);

					// Transforme la representation en objet json
					JSONObject jsObj = jsonRepr.getJsonObject();

					// Transforme en Implique
					implique = new Implique(jsObj);

					// Mise a jour de l'message
					impliques.set(i, implique);
					break;

				}
			}

			// Si le message n'est pas trouvé
			if (implique == null) {
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			}

		} else {
			// Pas d'id -> Erreur
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
		}

		// Pas besoin de retourner de représentation au client
		return null;
	}



}
