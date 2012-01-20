package org.agetac.server.client;

import org.agetac.common.Intervention;
import org.agetac.common.Message;
import org.agetac.server.client.ServerConnection;
import org.restlet.ext.json.JsonRepresentation;

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

		//IHM ihm = new IHM();
		
		ServerConnection serv = new ServerConnection("localhost", "8112", "agetacserver");
		
		// Création d'une intervention
		Intervention inter = new Intervention("inter");
		JsonRepresentation interRepresentation = new JsonRepresentation(inter.toJson());
		
		// Envoi sur le serveur		
		serv.putResource(INTERVENTION, inter.getUniqueID(), interRepresentation);
		
		// Maintenant on crée une connexion à cette intervention
		InterventionConnection interCon = new InterventionConnection(inter.getUniqueID(), serv);
		
		
		// Création d'un message 
		Message msg = new Message("1", "Bonjour", "1224");
		
		// Envoi du message a l'intervention
		interCon.putMessage(msg);
		
		// Récupération du message
		Message m = interCon.getMessage("1");
		System.out.println(m.getMessage());
		
		//TODO Coté serveur, enregistrer le message et le rendre accessible seulement pour la bonne intervention

		
		// Envoi du message pour l'intervention
		
		
		
		
		/*
		
		//Création d'un agent 
		Message msg = new Message("1", "Bonjour", "1224");
		JsonRepresentation resRepresentation = new JsonRepresentation(msg.toJson());
		
		//Envoi sur le serveur
		serv.putResource("message", msg.getUniqueID(), resRepresentation);
		
		//Récupération de la resource
		JsonRepresentation representation = new JsonRepresentation(serv.getResource("message", "1"));
		Message m = new Message(representation.getJsonObject());
		System.out.println(m.toJson());
		
		
		//Suppression de la resource
		serv.deleteResource("message", "1");
		
		//Test suppression
		serv.getResource("message", "1");
		 */
	}
}
