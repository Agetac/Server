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
		// Cr�e une representation JSON vide
		JsonRepresentation result = null;
		// R�cup�re l'identifiant unique de la ressource demand�e.
		String interId = (String) this.getRequestAttributes().get("interId");
		String cibId = (String) this.getRequestAttributes().get("cibleId");
		System.out.println(cibId);
		// R�cup�ration des cibles de l'intervention
		List<Cible> cibles = Interventions.getInstance().getIntervention(interId).getCibles();

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
				result = new JsonRepresentation(cible.toJson());
			}
		// Si on veut tous les cibles
		} else if (cibId == null) {
			
			JSONArray jsonAr = new JSONArray(); //Cr�ation d'une liste Json
			for(int i=0; i< cibles.size();i++){
				jsonAr.put(new JSONObject(cibles.get(i).toJson())); // On ajoute un jsonObject contenant le cible dans le jsonArray
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

		// R�cup�re la repr�sentation JSON du cible
		JsonRepresentation jsonRepr = new JsonRepresentation(representation);
		// System.out.println("JsonRepresentation : " + jsonRepr.getText());

		// Transforme la representation en objet java
		JSONObject jsObj = jsonRepr.getJsonObject();
		Cible cible = new Cible(jsObj);
		// System.out.println("Cible : " + cible.toJson());

		// Ajoute l'cible a la base de donn�e
		Intervention i = Interventions.getInstance().getIntervention(interId);
		List<Cible> lm = i.getCibles();
		lm.add(cible);
		// Cibles.getInstance().addCible(cible);
		// Pas besoin de retourner de repr�sentation au client
		return null;
	}

	@Override
	public Representation deleteResource() {
		// R�cup�re l'id dans l'url
		String interId = (String) this.getRequestAttributes().get("interId");
		String cibId = (String) this.getRequestAttributes().get("cibleId");
		
		// On s'assure qu'il n'est plus pr�sent en base de donn�es
	
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
