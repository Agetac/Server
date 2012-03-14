package org.agetac.server.resources.impl;

import java.util.List;

import org.agetac.common.model.impl.Intervention;
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
	 *         CLIENT_ERROR_NOT_ACCEPTABLE si l'id unique n'éxiste pas
	 * @throws Exception
	 *             En cas de problème de génération de représentation. Ne
	 *             devrait pas arriver en pratique mais si c'est le cas, Restlet
	 *             met le bon code status.
	 */
	@Get
	public Representation getResource() throws Exception {
		// Crée une representation JSON vide
		JsonRepresentation result = null;
		// Récupère l'identifiant unique de la ressource demandée.
		String interId = (String) this.getRequestAttributes().get("interId");
		String vehId = (String) this.getRequestAttributes().get("vehiculeId");
		
		// Récupération des vehicules de l'intervention
		List<Vehicule> vehicules = Interventions.getInstance().getIntervention(interId).getVehicules();

		Vehicule vehicule = null;
		
		// Si on demande un vehicule précis
		if (vehId != null) {
			// Recherche du vehicule demandé
			for (int i = 0; i < vehicules.size(); i++) {
				if (vehicules.get(i).getUniqueID().equals(vehId)) {
					vehicule = vehicules.get(i);
				}
			}
			// Si le vehicule n'est pas trouvé
			if (vehicule == null) {
				result = null;
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			} else {
				result = new JsonRepresentation(vehicule.toJSON());
			}
		// Si on veut tous les vehicules
		} else if (vehId == null) {
			
			JSONArray jsonAr = new JSONArray(); //Création d'une liste Json
			for(int i=0; i< vehicules.size();i++){
				jsonAr.put(vehicules.get(i).toJSON()); // On ajoute un jsonObject contenant le vehicule dans le jsonArray
			}
			
			result = new JsonRepresentation(jsonAr); // On crée la représentation de la liste
		}

		// Retourne la représentation, le code status indique au client si elle est valide
		return result;
	}

	/**
	 * Ajoute le vehicule transmit à la base de données interne.
	 * 
	 * @param representation
	 *            La représentation Json de l'vehicule
	 * @return null.
	 * @throws Exception
	 *             En cas de problème de lecture de la representation.
	 */
	@Put
	public Representation putResource(Representation representation)
			throws Exception {
		// Récupère l'identifiant unique de la ressource demandée.
		String interId = (String) this.getRequestAttributes().get("interId");

		// Récupère la représentation JSON du vehicule
		JsonRepresentation jsonRepr = new JsonRepresentation(representation);
		// System.out.println("JsonRepresentation : " + jsonRepr.getText());

		// Transforme la representation en objet java
		JSONObject jsObj = jsonRepr.getJsonObject();
		Vehicule vehicule = new Vehicule(jsObj);
		

		// Ajoute Vehicule a la base de donnée
		Intervention i = Interventions.getInstance().getIntervention(interId);
		List<Vehicule> lv = i.getVehicules();
		
		// On vérifie si le Vehicule n'éxiste pas déjà
		for(int ii=0; ii<lv.size(); ii++){
			if (lv.get(ii).getUniqueID().equals(vehicule.getUniqueID())) {
				// Vehicule trouvé, envois du code status 406
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
				return null;
			}
		}
		
		// Ajoute l'vehicule a la base de donnée
		lv.add(vehicule);
		
		// Vehicules.getInstance().addVehicule(vehicule);
		// Pas besoin de retourner de représentation au client
		return null;
	}

	/**
	 * Supprime le vehicule identifié de la base de données
	 * 
	 * @return null.
	 */
	@Delete
	public Representation deleteResource() {
		// Récupère l'id dans l'url
		String interId = (String) this.getRequestAttributes().get("interId");
		String vehId = (String) this.getRequestAttributes().get("vehiculeId");
		
		
		// On s'assure qu'il n'est plus présent en base de données
		
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
		// TODO Auto-generated method stub
		return null;
	}


}
