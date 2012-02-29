package org.agetac.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.agetac.client.model.SourceModel;
import org.agetac.client.view.SourceView;
import org.agetac.model.exception.InvalidJSONException;
import org.agetac.model.impl.Intervention;
import org.agetac.model.impl.Message;
import org.agetac.model.impl.Source;
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
		
		AgetacClient.testCommunication2();

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
	
	private static void testCommunication() throws JSONException{
		
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
	
	private static void testCommunication2() throws JSONException, IOException, InvalidJSONException{
		
		ServerConnection serv = new ServerConnection("localhost", "8112", "agetacserver");
		
		// Création d'une intervention
		Intervention inter = new Intervention("1");
		JsonRepresentation interRepresentation = new JsonRepresentation(inter.toJSON());
		serv.putResource(INTERVENTION, inter.getUniqueID(), interRepresentation);
		
		// Envoi sur le serveur
		ArrayList<Intervention> interv = new ArrayList<Intervention>();
		JsonRepresentation js = null;
		Intervention i = null;
		Representation repr = null;
		
		try {
			repr = serv.getResource(INTERVENTION, "1");
			js = new JsonRepresentation(repr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			if ( js.getText() == "null") i = new Intervention(js.getJsonObject());
		} catch (Exception e) {
			e.printStackTrace();
		}

		//System.out.println(js.getText());
		if ( js.getText() != "null") interv.add(i);

		if ( js.getText() == "null") serv.putResource(INTERVENTION, inter.getUniqueID(), interRepresentation);
		
		//TODO : Cote serveur, empecher les uniqueID non uniques :)
		
		// Maintenant on crée une connexion à cette intervention
		InterventionConnection interCon = new InterventionConnection(inter.getUniqueID(), serv);
		
		SourceModel srcModel = new SourceModel(interCon);
		SourceView srcView = new SourceView(srcModel);
		
	}
}
