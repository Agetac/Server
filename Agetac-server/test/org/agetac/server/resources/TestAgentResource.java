package org.agetac.server.resources;

import static org.junit.Assert.*;

import org.agetac.model.impl.Agent;
import org.agetac.server.AgetacServer;
import org.junit.BeforeClass;
import org.junit.Test;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;


public class TestAgentResource {

	/** Le port utilisé pour les tests. */
	private static int testPort = 8112;

	/**
	 * Démarre le serveur avant de tester quoi que ce soit.
	 * 
	 * @throws Exception
	 *             If problems occur starting up the server.
	 */
	@BeforeClass
	public static void startServer() throws Exception {
		AgetacServer.runServer(testPort);
	}

	/**
	 * Test qu'on ne peut pas récupérer d'agent inconnue sans lancer d'exception.
	 * 
	 * @throws Exception
	 *             Si pas d'exception, on a un problème.
	 */
	@Test(expected = ResourceException.class)
	public void testUnknownAgent() throws Exception {
		String testUrl = String.format(
				"http://localhost:%s/agetacserver/agent/foo", testPort);
		ClientResource client = new ClientResource(testUrl);
		client.get();
	}

	/**
	 * Test l'ajout, la récupération et la suppression d'un agent.
	 * 
	 * @throws Exception
	 *             En cas de problème.
	 */
	@Test
	public void testAddAgent() throws Exception {
		// Construction de l'url de test
		String uniqueID = "np";
		String testUrl = String.format(
				"http://localhost:%s/agetacserver/agent/%s", testPort,
				uniqueID);
		ClientResource client = new ClientResource(testUrl);

		// On construit la représentation JSON de l'agent
		Agent agent = new Agent(uniqueID, "Noel", Agent.Aptitude.CDG, null);
		JsonRepresentation representation = new JsonRepresentation(agent.toJSON());

		// On envoie (put) l'agent au serveur
		client.put(representation);

		// Maintenant ont essaye de récupérer ce meme agent
		JsonRepresentation representation2 = new JsonRepresentation(client.get());
		Agent agent2 = new Agent(representation2.getJsonObject());
		assertEquals("Vérification de l'id de l'agent récupéré", uniqueID,
				agent2.getUniqueID());

		// On le supprime.
		client.delete();

		// On s'assure qu'il n'éxiste plus.
		try {
			client.get();
			throw new Exception("L'agent n'éxiste pas !");
		} catch (Exception e) { 
		}
	}
}
