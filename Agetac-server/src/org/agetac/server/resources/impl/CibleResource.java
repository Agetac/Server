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
		
		// Cr�e une representation JSON vide
		JsonRepresentation result = null;
		// R�cup�re l'identifiant unique de la rescible demand�e.
		String interId = (String) this.getRequestAttributes().get("interId");
		String cibId = (String) this.getRequestAttributes().get("cibleId");
		System.out.println(cibId);
		// R�cup�ration des cibles de l'intervention
		List<Cible> cibles = Interventions.getInstance()
				.getIntervention(interId).getCibles();

		Cible cible = null;

		// Si on demande un cible pr�cis
		if (cibId != null) {
			// Recherche du cible demand�
			for (int i = 0; i < cibles.size(); i++) {
				if (cibles.get(i).getUniqueID().equals(cibId)) {
					cible = cibles.get(i);
				}
			}
			// Si le cible n'est pas trouv�
			if (cible == null) {
				result = null;
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			} else {
				result = new JsonRepresentation(cible.toJSON());
			}
			// Si on veut tous les cibles
		} else if (cibId == null) {

			JSONArray jsonAr = new JSONArray(); // Cr�ation d'une liste Json
			for (int i = 0; i < cibles.size(); i++) {
				jsonAr.put(cibles.get(i).toJSON()); // On ajoute un jsonObject
													// contenant le cible dans
													// le jsonArray
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
	public Representation putResource(Representation representation)
			throws Exception {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
		// R�cup�re l'identifiant unique de la rescible demand�e.
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
		
		// On retourne la repr�sentation au client
		return jsonRepr;
	}

	@Override
	public Representation deleteResource() {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
		// R�cup�re l'id dans l'url
		String interId = (String) this.getRequestAttributes().get("interId");
		String cibId = (String) this.getRequestAttributes().get("cibleId");

		// On s'assure qu'il n'est plus pr�sent en base de donn�es

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
		
		// R�cup�re l'identifiant unique de la rescible demand�e.
		String interId = (String) this.getRequestAttributes().get("interId");
		String cibleId = (String) this.getRequestAttributes().get("cibleId");

		// R�cup�ration des messages de l'intervention
		List<Cible> cibles = Interventions.getInstance().getIntervention(interId).getCibles();

		Cible cible = null;
		JsonRepresentation jsonRepr = null;
		// Si on demande un message pr�cis
		if (cibleId != null) {

			// Recherche de l'message demand�e
			for (int i = 0; i < cibles.size(); i++) {
				if (cibles.get(i).getUniqueID().equals(cibleId)) {

					// R�cup�re la repr�sentation JSON de l'message a mettre a
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

			// Si le message n'est pas trouv�
			if (cible == null) {
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			}

		} else {
			// Pas d'id -> Erreur
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
		}
		
		jsonRepr = new JsonRepresentation(cible.toJSON());
		// Pas besoin de retourner de repr�sentation au client
		return jsonRepr;
	}

}
