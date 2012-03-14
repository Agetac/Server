package org.agetac.server.resources.impl;

import java.util.List;

import org.agetac.common.model.impl.Intervention;
import org.agetac.common.model.impl.Position;
import org.agetac.common.model.impl.Source;
import org.agetac.server.db.Interventions;
import org.agetac.server.resources.sign.IServerResource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

public class SourceResource extends ServerResource implements IServerResource {

	@Override
	public Representation getResource() throws Exception {
		// Crée une representation JSON vide
		JsonRepresentation result = null;
		// Récupère l'identifiant unique de la ressource demandée.
		String interId = (String) this.getRequestAttributes().get("interId");
		String srcId = (String) this.getRequestAttributes().get("sourceId");
		// System.out.println(srcId);
		// Récupération des sources de l'intervention
		List<Source> sources = Interventions.getInstance()
				.getIntervention(interId).getSources();

		Source source = null;

		// Si on demande un source précis
		if (srcId != null) {
			// Recherche du source demandé
			for (int i = 0; i < sources.size(); i++) {
				if (sources.get(i).getUniqueID().equals(srcId)) {
					source = sources.get(i);
				}
			}
			// Si le source n'est pas trouvé
			if (source == null) {
				result = null;
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			} else {
				result = new JsonRepresentation(source.toJSON());
			}
			// Si on veut tous les sources
		} else if (srcId == null) {

			JSONArray jsonAr = new JSONArray(); // Création d'une liste Json
			for (int i = 0; i < sources.size(); i++) {
				jsonAr.put(sources.get(i).toJSON()); // On ajoute un jsonObject
														// contenant le source
														// dans le jsonArray
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
	public /*synchronized*/ Representation putResource(Representation representation)
			throws Exception {
		
		// Récupère l'identifiant unique de la ressource demandée.
		String interId = (String) this.getRequestAttributes().get("interId");

		Intervention i = Interventions.getInstance().getIntervention(interId);
		List<Source> lm = i.getSources();

		JsonRepresentation jsonRepr = new JsonRepresentation(representation);
		Source source = new Source(jsonRepr.getJsonObject());
		
		
		// Nouvel ID
		String uid = (lm.size() + 1) + "";
		source.setUniqueID(uid);
		

		// Ajoute la source a la base de donnée
		lm.add(source);
		
		// On retourne représentation au client
		jsonRepr = new JsonRepresentation(source.toJSON());
		return jsonRepr;
	}

	@Override
	public Representation deleteResource() {
		// Récupère l'id dans l'url
		String interId = (String) this.getRequestAttributes().get("interId");
		String srcId = (String) this.getRequestAttributes().get("sourceId");

		// On s'assure qu'il n'est plus présent en base de données
		Intervention inter = Interventions.getInstance().getIntervention(interId);
		List<Source> sources = inter.getSources();
		for (int i = 0; i < sources.size(); i++) {
			if (sources.get(i).getUniqueID().equals(srcId)) {
				sources.remove(sources.get(i));
			}
		}

		return null;
	}

	@Override
	public Representation postResource(Representation representation)
			throws Exception {
		// Récupère l'identifiant unique de la ressource demandée.
		String interId = (String) this.getRequestAttributes().get("interId");
		String srcId = (String) this.getRequestAttributes().get("sourceId");

		// Récupération des messages de l'intervention
		List<Source> sources = Interventions.getInstance()
				.getIntervention(interId).getSources();

		Source source = null;

		// Si on demande un message précis
		if (srcId != null) {

			// Recherche de l'message demandée
			for (int i = 0; i < sources.size(); i++) {
				if (sources.get(i).getUniqueID().equals(srcId)) {

					// Récupère la représentation JSON de l'message a mettre a
					// jour
					JsonRepresentation jsonRepr = new JsonRepresentation(
							representation);

					// Transforme la representation en objet json
					JSONObject jsObj = jsonRepr.getJsonObject();

					// Transforme en Message
					source = new Source(jsObj);

					// Mise a jour de l'message
					sources.set(i, source);
					break;

				}
			}

			// Si le message n'est pas trouvé
			if (source == null) {
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
