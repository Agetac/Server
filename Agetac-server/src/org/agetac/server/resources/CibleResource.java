package org.agetac.server.resources;

import java.util.List;

import org.agetac.common.Intervention;
import org.agetac.common.Cible;
import org.agetac.server.db.Interventions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

public class CibleResource extends ServerResource implements IServerResource {

	@Override
	public Representation getResource() throws Exception {
		// Crée une representation JSON vide
		JsonRepresentation result = null;
		// Récupère l'identifiant unique de la ressource demandée.
		String interId = (String) this.getRequestAttributes().get("interId");
		String cibId = (String) this.getRequestAttributes().get("cibleId");
		System.out.println(cibId);
		// Récupération des cibles de l'intervention
		List<Cible> cibles = Interventions.getInstance().getIntervention(interId).getCibles();

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
				result = new JsonRepresentation(cible.toJson());
			}
		// Si on veut tous les cibles
		} else if (cibId == null) {
			
			JSONArray jsonAr = new JSONArray(); //Création d'une liste Json
			for(int i=0; i< cibles.size();i++){
				jsonAr.put(new JSONObject(cibles.get(i).toJson())); // On ajoute un jsonObject contenant le cible dans le jsonArray
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

		// Récupère la représentation JSON du cible
		JsonRepresentation jsonRepr = new JsonRepresentation(representation);
		// System.out.println("JsonRepresentation : " + jsonRepr.getText());

		// Transforme la representation en objet java
		JSONObject jsObj = jsonRepr.getJsonObject();
		Cible cible = new Cible(jsObj);
		// System.out.println("Cible : " + cible.toJson());

		// Ajoute l'cible a la base de donnée
		Intervention i = Interventions.getInstance().getIntervention(interId);
		List<Cible> lm = i.getCibles();
		lm.add(cible);
		// Cibles.getInstance().addCible(cible);
		// Pas besoin de retourner de représentation au client
		return null;
	}

	@Override
	public Representation deleteResource() {
		// Récupère l'id dans l'url
		String interId = (String) this.getRequestAttributes().get("interId");
		String cibId = (String) this.getRequestAttributes().get("cibleId");
		
		// On s'assure qu'il n'est plus présent en base de données
	
		Intervention inter = Interventions.getInstance().getIntervention(interId);
		List<Cible> cibles = inter.getCibles();
		for (int i = 0; i < cibles.size(); i++) {
			if (cibles.get(i).getUniqueID().equals(cibId)) {
				cibles.remove(cibles.get(i));
			}
		}
		
		return null;
	}

}
