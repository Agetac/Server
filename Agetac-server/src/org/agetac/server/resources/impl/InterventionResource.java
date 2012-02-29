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
	 *         CLIENT_ERROR_NOT_ACCEPTABLE si l'id unique n'�xiste pas
	 * @throws Exception
	 *             En cas de probl�me de g�n�ration de repr�sentation. Ne
	 *             devrait pas arriver en pratique mais si c'est le cas, Restlet
	 *             met le bon code status.
	 */
	@Override
	public Representation getResource() throws Exception {
		// Cr�e une representation JSON vide
		JsonRepresentation result = null;
		// R�cup�re l'identifiant unique de la ressource demand�e.
		String uniqueID = (String) this.getRequestAttributes().get("uniqueID");
		
		if(uniqueID != null){
			
			Intervention intervention = null;
			
			// La recherche dans la base de donn�es.
			intervention = Interventions.getInstance().getIntervention(uniqueID);
			if (intervention == null) {
				// Ressource non-trouv�, envois du code status 406
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			} else {
				// Ressource trouv�, envoie de la repr�sentation Json
				result = new JsonRepresentation(intervention.toJSON());
				// Le code status par d�faut est 200 s'il n'est pas
			}

			
		// Si on veut toutes les interventions
		} else if (uniqueID == null) {
			
			List<Intervention> interventions = Interventions.getInstance().getInterventions();
			
			JSONArray jsonAr = new JSONArray(); //Cr�ation d'une liste Json
			for(int i=0; i < interventions.size(); i++){
				jsonAr.put(interventions.get(i).toJSON()); // On ajoute un jsonObject contenant le implique dans le jsonArray
			}
			
			result = new JsonRepresentation(jsonAr); // On cr�e la repr�sentation de la liste
			
		}
		
		// Retourne la repr�sentation, le code status indique au client si elle
		// est valide
		return result;
	}

	/**
	 * Ajoute l'intervention transmit � la base de donn�es interne.
	 * 
	 * @param representation
	 *           La repr�sentation Json de l'intervention
	 * @return null.
	 * @throws Exception
	 *             En cas de probl�me de lecture de la representation.
	 */
	@Override
	public Representation putResource(Representation representation)
			throws Exception {
		// R�cup�re la repr�sentation JSON de l'intervention
		JsonRepresentation jsonRepr = new JsonRepresentation(representation);
		// Transforme la representation en objet java
		Intervention intervention = new Intervention(jsonRepr.getJsonObject());
		
		// On v�rifie si l'intervention n'�xiste pas d�j�
		if (Interventions.getInstance().getIntervention(intervention.getUniqueID()) != null) {
			// Ressource non-trouv�, envois du code status 406
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
		}
		else{
			// Ajoute l'intervention a la base de donn�e
			Interventions.getInstance().addIntervention(intervention);
		}
		// Pas besoin de retourner de repr�sentation au client
		return null;
	}

	/**
	 * Supprime l'intervention identifi� de la base de donn�es
	 * 
	 * @return null.
	 */
	@Override
	public Representation deleteResource() {
		// R�cup�re l'id dans l'url
		String uniqueID = (String) this.getRequestAttributes().get("uniqueID");
		// On s'assure qu'il n'est plus pr�sent en base de donn�es
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
