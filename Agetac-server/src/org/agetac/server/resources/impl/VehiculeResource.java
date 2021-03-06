package org.agetac.server.resources.impl;

import java.util.List;

import org.agetac.common.model.impl.Intervention;
import org.agetac.common.model.impl.Source;
import org.agetac.common.model.impl.Vehicule;
import org.agetac.server.db.Interventions;
import org.agetac.server.resources.sign.IServerResource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

public class VehiculeResource extends ServerResource implements IServerResource{
	/**
	 * Retourne l'instance de du vehicule demander dans l'url
	 * 
	 * @return La representation de l'vehicule demander ou
	 *         CLIENT_ERROR_NOT_ACCEPTABLE si l'id unique n'�xiste pas
	 * @throws Exception
	 *             En cas de probl�me de g�n�ration de repr�sentation. Ne
	 *             devrait pas arriver en pratique mais si c'est le cas, Restlet
	 *             met le bon code status.
	 */
	@Get
	public Representation getResource() throws Exception {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
		// Cr�e une representation JSON vide
		JsonRepresentation result = null;
		// R�cup�re l'identifiant unique de la ressource demand�e.
		String interId = (String) this.getRequestAttributes().get("interId");
		String vehId = (String) this.getRequestAttributes().get("vehiculeId");
		
		// R�cup�ration des vehicules de l'intervention
		List<Vehicule> vehicules = Interventions.getInstance().getIntervention(interId).getVehicules();

		Vehicule vehicule = null;
		
		// Si on demande un vehicule pr�cis
		if (vehId != null) {
			// Recherche du vehicule demand�
			for (int i = 0; i < vehicules.size(); i++) {
				if (vehicules.get(i).getUniqueID().equals(vehId)) {
					vehicule = vehicules.get(i);
				}
			}
			// Si le vehicule n'est pas trouv�
			if (vehicule == null) {
				result = null;
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			} else {
				result = new JsonRepresentation(vehicule.toJSON());
			}
		// Si on veut tous les vehicules
		} else if (vehId == null) {
			
			JSONArray jsonAr = new JSONArray(); //Cr�ation d'une liste Json
			for(int i=0; i< vehicules.size();i++){
				jsonAr.put(vehicules.get(i).toJSON()); // On ajoute un jsonObject contenant le vehicule dans le jsonArray
			}
			
			result = new JsonRepresentation(jsonAr); // On cr�e la repr�sentation de la liste
		}

		// Retourne la repr�sentation, le code status indique au client si elle est valide
		return result;
	}

	/**
	 * Ajoute le vehicule transmit � la base de donn�es interne.
	 * 
	 * @param representation
	 *            La repr�sentation Json de l'vehicule
	 * @return null.
	 * @throws Exception
	 *             En cas de probl�me de lecture de la representation.
	 */
	@Put
	public Representation putResource(Representation representation)
			throws Exception {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
		// R�cup�re l'identifiant unique de la ressource demand�e.
		String interId = (String) this.getRequestAttributes().get("interId");
		
		// Ajoute Vehicule a la base de donn�e
		Intervention i = Interventions.getInstance().getIntervention(interId);
		List<Vehicule> lv = i.getVehicules();
		
		// R�cup�re la repr�sentation JSON du vehicule
		JsonRepresentation jsonRepr = new JsonRepresentation(representation);
		Vehicule vehicule = new Vehicule(jsonRepr.getJsonObject());
		
		String uid = (lv.size() + 1) + "";
		vehicule.setUniqueID(uid);
		
		// Ajoute l'vehicule a la base de donn�e
		lv.add(vehicule);

		// retourne une repr�sentation au client
		return new JsonRepresentation(vehicule.toJSON());
	}

	/**
	 * Supprime le vehicule identifi� de la base de donn�es
	 * 
	 * @return null.
	 */
	@Delete
	public Representation deleteResource() {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
		// R�cup�re l'id dans l'url
		String interId = (String) this.getRequestAttributes().get("interId");
		String vehId = (String) this.getRequestAttributes().get("vehiculeId");
		
		
		// On s'assure qu'il n'est plus pr�sent en base de donn�es
		
		Intervention inter = Interventions.getInstance().getIntervention(interId);
		List<Vehicule> vehicules = inter.getVehicules();
		for (int i = 0; i < vehicules.size(); i++) {
			if (vehicules.get(i).getUniqueID().equals(vehId)) {
				vehicules.remove(vehicules.get(i));
			}
		}
		
		return null;
	}

	@Override
	public Representation postResource(Representation representation)
			throws Exception {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
		// R�cup�re l'identifiant unique de la ressource demand�e.
		String interId = (String) this.getRequestAttributes().get("interId");
		String vehiculeId = (String) this.getRequestAttributes().get("vehiculeId");

		// R�cup�ration des messages de l'intervention
		List<Vehicule> vehicules = Interventions.getInstance().getIntervention(interId).getVehicules();

		Vehicule vehicule = null;
		JsonRepresentation jsonRepr = null;
		// Si on demande un message pr�cis
		if (vehiculeId != null) {

			// Recherche de l'message demand�e
			for (int i = 0; i < vehicules.size(); i++) {
				if (vehicules.get(i).getUniqueID().equals(vehiculeId)) {

					// R�cup�re la repr�sentation JSON de l'message a mettre a jour
					 jsonRepr = new JsonRepresentation(representation);

					// Transforme la representation en objet json
					JSONObject jsObj = jsonRepr.getJsonObject();

					// Transforme en Message
					vehicule = new Vehicule(jsObj);

					// Mise a jour de l'message
					vehicules.set(i, vehicule);
					break;

				}
			}

			// Si le message n'est pas trouv�
			if (vehicule == null) {
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			}

		} else {
			// Pas d'id -> Erreur
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
		}
		jsonRepr = new JsonRepresentation(vehicule.toJSON());
		// Pas besoin de retourner de repr�sentation au client
		return jsonRepr;
	}


}
