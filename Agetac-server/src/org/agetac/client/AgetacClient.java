package org.agetac.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.agetac.client.api.ServerConnection;
import org.agetac.client.model.MessageModel;
import org.agetac.client.model.SourceModel;
import org.agetac.client.view.MessageView;
import org.agetac.client.view.SourceView;
import org.agetac.common.api.InterventionConnection;
import org.agetac.common.exception.BadResponseException;
import org.agetac.common.exception.InvalidJSONException;
import org.agetac.common.model.impl.Action;
import org.agetac.common.model.impl.Action.ActionType;
import org.agetac.common.model.impl.Cible;
import org.agetac.common.model.impl.DemandeMoyen;
import org.agetac.common.model.impl.Groupe;
import org.agetac.common.model.impl.Intervention;
import org.agetac.common.model.impl.Message;
import org.agetac.common.model.impl.Position;
import org.agetac.common.model.impl.Source;
import org.agetac.common.model.impl.DemandeMoyen.EtatDemande;
import org.agetac.common.model.impl.Vehicule;
import org.agetac.common.model.impl.Vehicule.CategorieVehicule;
import org.agetac.common.model.impl.Vehicule.EtatVehicule;
import org.json.JSONArray;
import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;

/**
 * A simple example of a "client" class for the AgetacServer.
 * 
 */
public class AgetacClient {

	
	public static final String INTERVENTION = "intervention";
	public static final String MESSAGE = "message";
	public static final String AGENT = "agent";
	public static final String CIBLE = "cible";
	
	/**
	 * Connect to a running AgetacServer and perform the requested operation.
	 * 
	 * @param args
	 * @throws Exception
	 *             If problems occur.
	 */
	public static void main(String[] args) throws Exception {
		
		AgetacClient.initIntervention();
		//AgetacClient.testCommunication2();

		/*MessageModel msgModel = new MessageModel();
		MessageView msgView = new MessageView(msgModel);

		AgentModel agentModel = new AgentModel();
		AgentView agentView = new AgentView(agentModel);
		VehiculeModel vehiculeModel = new VehiculeModel();
		VehiculeView vehiculeView = new VehiculeView(vehiculeModel);
		InterventionModel interventionModel = new InterventionModel();
		InterventionView interventionView = new InterventionView(interventionModel);
		CaserneModel casModel = new CaserneModel();
		CaserneView casView = new CaserneView(casModel);
		SourceModel srcModel = new SourceModel();
		SourceView srcView = new SourceView(srcModel);*/
	}

	private static void initIntervention() throws BadResponseException, JSONException, IOException, InvalidJSONException {
		
		ServerConnection serv = new ServerConnection("localhost", "8112", "agetacserver");
		
		Intervention inter = new Intervention();
		inter.setPosition(new Position(4811551,-1638774));
		inter.setName("Demo");
		
		JsonRepresentation interRepresentation = new JsonRepresentation(serv.putResource(INTERVENTION, null, new JsonRepresentation(inter.toJSON())));
		inter = new Intervention(interRepresentation.getJsonObject());
		
		InterventionConnection interCon = new InterventionConnection(inter.getUniqueID(), serv);
		System.out.println(inter.toString());
		
		
		// Ajout de véhicule
		
		Groupe g1 = new Groupe("1", null, null);
		Vehicule v1 = new Vehicule(null, inter.getPosition(), CategorieVehicule.FPT, "Rennes", EtatVehicule.SUR_LES_LIEUX, g1, "0351");
		Vehicule v2 = new Vehicule(null, inter.getPosition(), CategorieVehicule.CCGCLC, "Rennes", EtatVehicule.SUR_LES_LIEUX, g1, "0351");
		Vehicule v3 = new Vehicule(null, inter.getPosition(), CategorieVehicule.VLCC, "Rennes", EtatVehicule.SUR_LES_LIEUX, g1, "0351");
		v1 = interCon.putVehicule(v1);
		v2 = interCon.putVehicule(v2);
		v3 = interCon.putVehicule(v3);
		System.out.println(v3.toString());
		interCon.deleteVehicule(v3);
		
		// Ajout action
		
		Action a1 = new Action(null, inter.getPosition(), ActionType.FIRE, inter.getPosition(), inter.getPosition());
		a1 = interCon.putAction(a1);
		a1.setPosition(new Position(4811552,-1638524));
		a1 = interCon.postAction(a1);
		
		// Ajout source
		
		Source s1 = new Source(null, inter.getPosition()); // Il faudrait ajouté un type comme les actions ? (FIRE ...)
		s1 = interCon.putSource(s1);
		s1.setPosition(new Position(4811589,-1638650));
		s1 = interCon.postSource(s1);
		interCon.deleteSource(s1);
		// Ajout source
		
		Cible c1 = new Cible(null, inter.getPosition()); // Il faudrait ajouté un type comme les actions ? (FIRE ...)
		c1 = interCon.putCible(c1);
		c1.setPosition(new Position(4811420,-1638032));
		c1 = interCon.postCible(c1);
		interCon.deleteCible(c1);
		//Création d'une demande
		
		/*
		DemandeMoyen demande = new DemandeMoyen(null, new Position(481523,-1638775), CategorieVehicule.CCFM, EtatDemande.LANCEE, new Groupe("1", null, null), "0101");
		interCon.putDemandeMoyen(demande);
		
		interRepresentation = new JsonRepresentation(serv.getResource(INTERVENTION, inter.getUniqueID()));
		inter = new Intervention(interRepresentation.getJsonObject());
		
		
		*/
		inter = interCon.getIntervention();
		System.out.println(inter);
	}
	
	
	
	
	private static void testCommunication() throws BadResponseException, JSONException {
		
		ServerConnection serv = new ServerConnection("localhost", "8112", "agetacserver");
		
		// Création d'une intervention
		Intervention inter = new Intervention("1");
		JsonRepresentation interRepresentation = new JsonRepresentation(inter.toJSON());
		
		// Envoi sur le serveur		
		serv.putResource(INTERVENTION, inter.getUniqueID(), interRepresentation);
		//TODO : Cote serveur, empecher les uniqueID non uniques :)
		
		// Maintenant on crée une connexion à cette intervention
		InterventionConnection interCon = new InterventionConnection(inter.getUniqueID(), serv);
		
		
		/**
		 * MESSAGES
		 */
		// Création d'un message 
		Message msg = new Message("4", "Ceci est un message", "1224");
		// Envoi du message a l'intervention
		System.out.println("Envoi d'un message");
		interCon.putMessage(msg);
		
		// Récupération du message
		System.out.println("Récupération des messages");
		List<Message> messages = interCon.getMessages();
		
		System.out.println("Affichage des messages :");
		
		for(int i=0; i<messages.size(); i++){
			System.out.println(" - " + messages.get(i).getUniqueID() + " : " + messages.get(i).getMessage());
		}
		
		// Modification d'un message
		msg.setMessage("Message modifié");
		msg.setDate("0102");
		interCon.postMessage(msg);
		
		// Récupération du message
		System.out.println("Récupération des messages");
		messages = interCon.getMessages();
		
		System.out.println("Affichage des messages :");
		
		for(int i=0; i<messages.size(); i++){
			System.out.println(" - " + messages.get(i).getUniqueID() + " : " + messages.get(i).getMessage());
		}
		
		/**
		 * VEHICULES
		 */

		/*
		Caserne c = new Caserne("1", "Janzé", null);

		
		Agent bob = new Agent("a1", "Bob", Agent.Aptitude.CDG, new ArrayList<Agent>());
		Groupe g1 = new Groupe("g", bob, new ArrayList<Agent>(), new ArrayList<Vehicule>());
		
		// Création d'un vehicule 
		Vehicule v1 = new Vehicule("v1", "FPT1", new Position(48.12244, 54.24444), c.getName(), Vehicule.EtatVehicule.ALERTE, g1);
		// Envoi du message a l'intervention
		System.out.println("Envoi d'un vehicule");
		interCon.putVehicule(v1);
		
		// Récupération du message
		System.out.println("Récupération des vehicules");
		List<Vehicule> vehicules = interCon.getVehicules();
		
		System.out.println("Affichage des vehicules :");
		
		for(int i=0; i<messages.size(); i++){
			System.out.println(" - " + vehicules.get(i).getUniqueID() + " : " + vehicules.get(i).getName());
		}
		*/
	
	}
	
	private static void testCommunication2() throws JSONException, IOException, InvalidJSONException, BadResponseException{
		
		ServerConnection serv = new ServerConnection("localhost", "8112", "agetacserver");
		
		// Création d'une intervention
		Intervention inter = new Intervention("1");
		JsonRepresentation interRepresentation = new JsonRepresentation(inter.toJSON());
		
		// Envoi sur le serveur
		Representation repr = serv.getResource(INTERVENTION, null);
		JsonRepresentation representation = new JsonRepresentation(repr);
		JSONArray ar = representation.getJsonArray();
		System.out.println(ar.toString());
		
		List<Intervention> interventions = new ArrayList<Intervention>();
		for (int i=0; i< ar.length(); i++){
			interventions.add(new Intervention(ar.getJSONObject(i)));
		}
		
		boolean doublon = false;
		for (Intervention inte : interventions ){
			doublon = inte.getUniqueID().equals(inter.getUniqueID()) || doublon;
		}

		if (!doublon) serv.putResource(INTERVENTION, inter.getUniqueID(), interRepresentation);
		
		//TODO : Cote serveur, empecher les uniqueID non uniques :)
		
		// Maintenant on crée une connexion à cette intervention
		InterventionConnection interCon = new InterventionConnection(inter.getUniqueID(), serv);
		
		MessageModel srcModel = new MessageModel(interCon);
		MessageView srcView = new MessageView(srcModel);
		
	}
	
private static void testCommunication3() throws JSONException, IOException, InvalidJSONException, BadResponseException{
		
		ServerConnection serv = new ServerConnection("localhost", "8112", "agetacserver");
		
		// Création d'une intervention
		Intervention inter = new Intervention("1");
		JsonRepresentation interRepresentation = new JsonRepresentation(inter.toJSON());
		
		// Envoi sur le serveur		
		serv.putResource(INTERVENTION, inter.getUniqueID(), interRepresentation);
		//TODO : Cote serveur, empecher les uniqueID non uniques :)
		
		Representation repr = serv.getResource(INTERVENTION, null);
		
		JsonRepresentation representation = new JsonRepresentation(repr);
		JSONArray ar = representation.getJsonArray();
		
		List<Intervention> interventions = new ArrayList<Intervention>();
		for(int i=0; i< ar.length(); i++){
			
			interventions.add(new Intervention(ar.getJSONObject(i)));
		}
		
		for(int i=0; i<interventions.size(); i++){
			System.out.println(interventions.get(i).toString());
		}
	}
}
