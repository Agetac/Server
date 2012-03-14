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
		// Cr�e une representation JSON vide
		JsonRepresentation result = null;
		// R�cup�re l'identifiant unique de la ressource demand�e.
		String interId = (String) this.getRequestAttributes().get("interId");
		String srcId = (String) this.getRequestAttributes().get("sourceId");
		// System.out.println(srcId);
		// R�cup�ration des sources de l'intervention
		List<Source> sources = Interventions.getInstance()
				.getIntervention(interId).getSources();

		Source source = null;

		// Si on demande un source pr�cis
		if (srcId != null) {
			// Recherche du source demand�
			for (int i = 0; i < sources.size(); i++) {
				if (sources.get(i).getUniqueID().equals(srcId)) {
					source = sources.get(i);
				}
			}
			// Si le source n'est pas trouv�
			if (source == null) {
				result = null;
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			} else {
				result = new JsonRepresentation(source.toJSON());
			}
			// Si on veut tous les sources
		} else if (srcId == null) {

			JSONArray jsonAr = new JSONArray(); // Cr�ation d'une liste Json
			for (int i = 0; i < sources.size(); i++) {
				jsonAr.put(sources.get(i).toJSON()); // On ajoute un jsonObject
														// contenant le source
														// dans le jsonArray
			}

			result = new JsonRepresentation(jsonAr); // On cr�e la
														// repr�sentation de la
														// liste
		}

		// Retourne la repr�sentation, le code status indique au client si elle
		// est valide
		return result;
	}

	@Override
	public /*synchronized*/ Representation putResource(Representation representation)
			throws Exception {
		
		// R�cup�re l'identifiant unique de la ressource demand�e.
		String interId = (String) this.getRequestAttributes().get("interId");

		Intervention i = Interventions.getInstance().getIntervention(interId);
		List<Source> lm = i.getSources();

		JsonRepresentation jsonRepr = new JsonRepresentation(representation);
		Source source = new Source(jsonRepr.getJsonObject());
		
		
		// Nouvel ID
		String uid = (lm.size() + 1) + "";
		source.setUniqueID(uid);
		

		// Ajoute la source a la base de donn�e
		lm.add(source);
		
		// On retourne repr�sentation au client
		jsonRepr = new JsonRepresentation(source.toJSON());
		return jsonRepr;
	}

	@Override
	public Representation deleteResource() {
		// R�cup�re l'id dans l'url
		String interId = (String) this.getRequestAttributes().get("interId");
		String srcId = (String) this.getRequestAttributes().get("sourceId");

		// On s'assure qu'il n'est plus pr�sent en base de donn�es
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
		// R�cup�re l'identifiant unique de la ressource demand�e.
		String interId = (String) this.getRequestAttributes().get("interId");
		String srcId = (String) this.getRequestAttributes().get("sourceId");

		// R�cup�ration des messages de l'intervention
		List<Source> sources = Interventions.getInstance()
				.getIntervention(interId).getSources();

		Source source = null;

		// Si on demande un message pr�cis
		if (srcId != null) {

			// Recherche de l'message demand�e
			for (int i = 0; i < sources.size(); i++) {
				if (sources.get(i).getUniqueID().equals(srcId)) {

					// R�cup�re la repr�sentation JSON de l'message a mettre a
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

			// Si le message n'est pas trouv�
			if (source == null) {
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			}

		} else {
			// Pas d'id -> Erreur
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
		}

		// Pas besoin de retourner de repr�sentation au client
		return null;
	}

}
