package org.agetac.server.resources.impl;

import org.agetac.model.impl.Intervention;
import org.agetac.server.db.Interventions;
import org.agetac.server.resources.sign.IServerResource;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
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
	@Get
	public Representation getResource() throws Exception {
		// Cr�e une representation JSON vide
		JsonRepresentation result = null;
		// R�cup�re l'identifiant unique de la ressource demand�e.
		String uniqueID = (String) this.getRequestAttributes().get("uniqueID");
		// System.out.println(uniqueID);
		// La recherche dans la base de donn�es.
		Intervention intervention = Interventions.getInstance().getIntervention(uniqueID);
		if (intervention == null) {
			// Ressource non-trouv�, envois du code status 406
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
		} else {
			// Ressource trouv�, envoie de la repr�sentation Json
			result = new JsonRepresentation(intervention.toJson());
			// Le code status par d�faut est 200 s'il n'est pas
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
	@Put
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
	@Delete
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
