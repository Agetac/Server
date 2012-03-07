package org.agetac.server.resources;

import static org.junit.Assert.*;

import org.agetac.model.impl.Agent;
import org.agetac.model.impl.Intervention;
import org.agetac.server.AgetacServer;
import org.junit.BeforeClass;
import org.junit.Test;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class TestInterventionResource {

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
	public void testUnknownIntervention() throws Exception {
		String testUrl = String.format("http://localhost:%s/agetacserver/intervention/test", testPort);
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
	public void testAddIntervention() throws Exception {
		// Construction de l'url de test
		String uniqueID = "test";
		String testUrl = String.format(
				"http://localhost:%s/agetacserver/intervention/%s", testPort,
				uniqueID);
		ClientResource client = new ClientResource(testUrl);

		// On construit la représentation JSON de la ressource testée 
		Intervention test_inter1 = new Intervention("test");
		JsonRepresentation representation = new JsonRepresentation(test_inter1.toJSON());

		// On envoie (put) la ressource au serveur
		client.put(representation);

		
		// Maintenant ont essaye de récupérer cette meme ressource
		JsonRepresentation representation2 = new JsonRepresentation(client.get());
		
		Intervention test_inter2 = new Intervention(representation2.getJsonObject());
		
		assertEquals("Vérification de l'id de la ressource récupérée", uniqueID, test_inter2.getUniqueID());

		// On le supprime.
		client.delete();

		// On s'assure qu'il n'éxiste plus.
		try {
			client.get();
			throw new Exception("L'intervention n'éxiste pas !");
		} catch (Exception e) { 
		}
	}

}
