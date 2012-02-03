package org.agetac.server.resources;

import java.util.List;

import org.agetac.model.impl.Caserne;
import org.agetac.server.db.Casernes;
import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

public class CaserneResource extends ServerResource implements IServerResource {

	public Representation getResource() throws Exception {
		// Cr�e une representation JSON vide
		JsonRepresentation result = null;
		// R�cup�re l'identifiant unique de la ressource demand�e.
		String casId = (String) this.getRequestAttributes().get("caserneId");
		System.out.println(casId);
		// R�cup�ration des casernes de l'intervention
		List<Caserne> casernes = Casernes.getInstance().getCasernes();

		Caserne caserne = null;

		// Si on demande une caserne pr�cise
		if (casId != null) {
			// Recherche de la caserne demand�e
			for (int i = 0; i < casernes.size(); i++) {
				if (casernes.get(i).getUniqueID().equals(casId)) {
					caserne = casernes.get(i);
				}
			}
			// Si la caserne n'est pas trouv�
			if (caserne == null) {
				result = null;
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			} else {
				result = new JsonRepresentation(caserne.toJson());
			}
			// Si on veut toutes les casernes
		} else if (casId == null) {

			JSONArray jsonAr = new JSONArray(); // Cr�ation d'une liste Json
			for (int i = 0; i < casernes.size(); i++) {
				// On ajoute un jsonObject contenant la caserne dans le jsonArray
				jsonAr.put(new JSONObject(casernes.get(i).toJson()));
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
		
		// R�cup�re la repr�sentation JSON du caserne
		JsonRepresentation jsonRepr = new JsonRepresentation(representation);

		// Transforme la representation en objet java
		JSONObject jsObj = jsonRepr.getJsonObject();
		Caserne caserne = new Caserne(jsObj);

		// Ajoute l'caserne a la base de donn�e
		Casernes.getInstance().addCaserne(caserne);
		// Pas besoin de retourner de repr�sentation au client
		return null;
	}

	@Override
	public Representation deleteResource() {
		// R�cup�re l'id dans l'url
		String interId = (String) this.getRequestAttributes().get("interId");
		String casId = (String) this.getRequestAttributes().get("caserneId");
		
		
		// On s'assure qu'il n'est plus pr�sent en base de donn�es
		Casernes.getInstance().deleteCaserne(casId);
		
		//for (int i = 0; i < casernes.size(); i++) {
		//	if (casernes.get(i).getUniqueID().equals(casId)) {
		//		casernes.remove(casernes.get(i));
		//	}
		//}
		
		return null;
	}

}
