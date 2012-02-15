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

	/** Le port utilis� pour les tests. */
	private static int testPort = 8112;

	/**
	 * D�marre le serveur avant de tester quoi que ce soit.
	 * 
	 * @throws Exception
	 *             If problems occur starting up the server.
	 */
	@BeforeClass
	public static void startServer() throws Exception {
		AgetacServer.runServer(testPort);
	}

	/**
	 * Test qu'on ne peut pas r�cup�rer d'agent inconnue sans lancer d'exception.
	 * 
	 * @throws Exception
	 *             Si pas d'exception, on a un probl�me.
	 */
	@Test(expected = ResourceException.class)
	public void testUnknownAgent() throws Exception {
		String testUrl = String.format(
				"http://localhost:%s/agetacserver/agent/foo", testPort);
		ClientResource client = new ClientResource(testUrl);
		client.get();
	}

	/**
	 * Test l'ajout, la r�cup�ration et la suppression d'un agent.
	 * 
	 * @throws Exception
	 *             En cas de probl�me.
	 */
	@Test
	public void testAddAgent() throws Exception {
		// Construction de l'url de test
		String uniqueID = "np";
		String testUrl = String.format(
				"http://localhost:%s/agetacserver/agent/%s", testPort,
				uniqueID);
		ClientResource client = new ClientResource(testUrl);

		// On construit la repr�sentation JSON de l'agent
		Agent agent = new Agent(uniqueID, "Noel", Agent.Aptitude.CDG, null);
		JsonRepresentation representation = new JsonRepresentation(agent.toJSON());

		// On envoie (put) l'agent au serveur
		client.put(representation);

		// Maintenant ont essaye de r�cup�rer ce meme agent
		JsonRepresentation representation2 = new JsonRepresentation(client.get());
		Agent agent2 = new Agent(representation2.getJsonObject());
		assertEquals("V�rification de l'id de l'agent r�cup�r�", uniqueID,
				agent2.getUniqueID());

		// On le supprime.
		client.delete();

		// On s'assure qu'il n'�xiste plus.
		try {
			client.get();
			throw new Exception("L'agent n'�xiste pas !");
		} catch (Exception e) { 
		}
	}
}
