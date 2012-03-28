package org.agetac.server.resources.impl;

import java.util.List;

import org.agetac.common.model.impl.DemandeMoyen;
import org.agetac.common.model.impl.DemandeMoyen.EtatDemande;
import org.agetac.common.model.impl.Groupe;
import org.agetac.common.model.impl.Intervention;
import org.agetac.common.model.impl.Position;
import org.agetac.common.model.impl.Vehicule;
import org.agetac.common.model.impl.Vehicule.EtatVehicule;
import org.agetac.server.db.Interventions;
import org.agetac.server.resources.sign.IServerResource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

public class DemandeResource extends ServerResource implements IServerResource {

	@Override
	public Representation getResource() throws Exception {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
		// Crée une representation JSON vide
		JsonRepresentation result = null;
		
		// Récupère l'identifiant unique de la ressource demandée.
		String interId = (String) this.getRequestAttributes().get("interId");
		String demId = (String) this.getRequestAttributes().get("demId");
		
		// Récupération des actions de l'intervention
		List<DemandeMoyen> demandes = Interventions.getInstance().getIntervention(interId).getDemandesMoyen();

		DemandeMoyen demande = null;
		
		// Si on demande un action précis
		if (demId != null) {
			// Recherche du action demandé
			for (int i = 0; i < demandes.size(); i++) {
				if (demandes.get(i).getUniqueID().equals(demId)) {
					demande = demandes.get(i);
				}
			}
			// Si l'action n'est pas trouvé
			if (demande == null) {
				result = null;
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			} else {
				result = new JsonRepresentation(demande.toJSON());
			}
		// Si on veut toutes les actions
		} else if (demId == null) {
			
			JSONArray jsonAr = new JSONArray(); //Création d'une liste Json
			for(int i=0; i< demandes.size();i++){
				jsonAr.put(demandes.get(i).toJSON()); // On ajoute un jsonObject contenant le action dans le jsonArray
			}
			
			result = new JsonRepresentation(jsonAr); // On crée la représentation de la liste
		}

		// Retourne la représentation, le code status indique au client si elle est valide
		return result;
	}

	@Override
	public Representation putResource(Representation representation)
			throws Exception {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
		// Récupère l'identifiant unique de la ressource demandée.
		String interId = (String) this.getRequestAttributes().get("interId");
		
		Intervention i = Interventions.getInstance().getIntervention(interId);
		List<DemandeMoyen> ld = i.getDemandesMoyen();

		DemandeMoyen demande;
		JsonRepresentation jsonRepr;
			
		// Nouvel ID
		String uid = (ld.size() + 1) + "";
		
		// Récupère la représentation JSON du action
		jsonRepr = new JsonRepresentation(representation);

		// Transforme la representation en objet java
		JSONObject jsObj = jsonRepr.getJsonObject();
		demande = new DemandeMoyen(jsObj);
		demande.setUniqueID(uid);
		
		//Ajout du action
		ld.add(demande);
		// On retourne la nouvelle représentation au client
		jsonRepr = new JsonRepresentation(demande.toJSON());
		
		
		// TODO: C'est du bricolage
		
		demande.setEtat(EtatDemande.ACCEPTEE);
		demande.getGroupesHoraires().put(EtatDemande.ACCEPTEE, "0102");
		
		//On crée le Véhicule demandé (normalement cherché dans la bdd de vehicules)
		Vehicule v = new Vehicule(""+(i.getVehicules().size()+1), demande.getPosition(),demande.getCategorie(), "Janze", EtatVehicule.ALERTE, new Groupe(demande.getGroupe(), null, null), "0103" );
		demande.setVehId(v.getUniqueID());
		i.getVehicules().add(v);
		
		// Fin bricolage
		
		// On retourne la représentation au client
		return jsonRepr;
		
	}
	
	@Override
	public Representation deleteResource() {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
		// Récupère l'id dans l'url
		String interId = (String) this.getRequestAttributes().get("interId");
		String demId = (String) this.getRequestAttributes().get("demId");
		
		
		// On s'assure qu'il n'est plus présent en base de données
		
		Intervention inter = Interventions.getInstance().getIntervention(interId);
		List<DemandeMoyen> demandes = inter.getDemandesMoyen();
		for (int i = 0; i < demandes.size(); i++) {
			if (demandes.get(i).getUniqueID().equals(demId)) {
				demandes.remove(demandes.get(i));
			}
		}
		
		return null;
	}

	@Override
	public Representation postResource(Representation representation)
			throws Exception {
		
		if (getResponse().getStatus() == Status.CLIENT_ERROR_UNAUTHORIZED) return null;
		
		// Récupère l'identifiant unique de la ressource demandée.
		String interId = (String) this.getRequestAttributes().get("interId");
		String demId = (String) this.getRequestAttributes().get("demId");
		
		// Récupération des actions de l'intervention
		List<DemandeMoyen> demandes = Interventions.getInstance().getIntervention(interId).getDemandesMoyen();
		
		DemandeMoyen demande = null;
		JsonRepresentation jsonRepr = null;
		// Si on demande un action précis
		if (demId != null) {
		
			// Recherche de l'action demandée
			for (int i = 0; i < demandes.size(); i++) {
				if (demandes.get(i).getUniqueID().equals(demId)) {
					
					// Récupère la représentation JSON de l'action a mettre a jour
					 jsonRepr = new JsonRepresentation(representation);

					// Transforme la representation en objet json
					JSONObject jsObj = jsonRepr.getJsonObject();
					// Transforme en Action
					demande = new DemandeMoyen(jsObj);
					
					// Mise a jour de l'action
					demandes.set(i, demande);
					break;
					
				}
			}
			
			// Si le action n'est pas trouvé
			if (demande == null) {
				getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
			}
			
			
		}else{
			// Pas d'id -> Erreur
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
		}
		jsonRepr = new JsonRepresentation(demande.toJSON());
		// Pas besoin de retourner de représentation au client
		return jsonRepr;
	}

}
