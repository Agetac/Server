package org.agetac.server.resources.impl;

import java.util.List;

import org.agetac.model.impl.Intervention;
import org.agetac.server.db.Interventions;
import org.agetac.server.resources.sign.IServerResource;
import org.json.JSONArray;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

public class InterventionResource  extends ServerResource implements IServerResource{
	/**
	 * Retourne l'instance de l'intervention demander dans l'url
	 * 
	 * @return La representation de l'intervention demander ou
	 *         CLIENT_ERROR_NOT_ACCEPTABLE si l'id unique n'éxiste pas
	 * @throws Exception
	 *             En cas de problème de génération de représentation. Ne
	 *             devrait pas arriver en pratique mais si c'est le cas, Restlet
	 *             met le bon code status.
	 */
	@Override
	public Representation getResource() throws Exception {
		// Crée une representation JSON vide
		JsonRepresentation result = null;
		// Récupère l'identifiant unique de la ressource demandée.
		String uniqueID = (String) this.getRequestAttributes().get("uniqueID");
		
		if(uniqueID != null){
			
			Intervention intervention = null;
			
			// La recherche dans la base de données.
			intervention = Interventions.getInstance().getIntervention(uniqueID);
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
			
			JSONArray jsonAr = new JSONArray(); //Création d'une liste Json
			for(int i=0; i < interventions.size(); i++){
				jsonAr.put(interventions.get(i).toJSON()); // On ajoute un jsonObject contenant le implique dans le jsonArray
			}
			
			result = new JsonRepresentation(jsonAr); // On crée la représentation de la liste
			
		}
		
		// Retourne la représentation, le code status indique au client si elle
		// est valide
		return result;
	}

	/**
	 * Ajoute l'intervention transmit à la base de données interne.
	 * 
	 * @param representation
	 *           La représentation Json de l'intervention
	 * @return null.
	 * @throws Exception
	 *             En cas de problème de lecture de la representation.
	 */
	@Override
	public Representation putResource(Representation representation)
			throws Exception {
		// Récupère la représentation JSON de l'intervention
		JsonRepresentation jsonRepr = new JsonRepresentation(representation);
		// Transforme la representation en objet java
		Intervention intervention = new Intervention(jsonRepr.getJsonObject());
		
		// On vérifie si l'intervention n'éxiste pas déjà
		if (Interventions.getInstance().getIntervention(intervention.getUniqueID()) != null) {
			// Ressource non-trouvé, envois du code status 406
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
		}
		else{
			// Ajoute l'intervention a la base de donnée
			Interventions.getInstance().addIntervention(intervention);
		}
		// Pas besoin de retourner de représentation au client
		return null;
	}

	/**
	 * Supprime l'intervention identifié de la base de données
	 * 
	 * @return null.
	 */
	@Override
	public Representation deleteResource() {
		// Récupère l'id dans l'url
		String uniqueID = (String) this.getRequestAttributes().get("uniqueID");
		// On s'assure qu'il n'est plus présent en base de données
		Interventions.getInstance().deleteIntervention(uniqueID);
		return null;
	}

	@Override
	public Representation postResource(Representation representation)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
