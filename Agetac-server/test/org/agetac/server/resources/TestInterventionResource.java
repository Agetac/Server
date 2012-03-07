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
	public void testUnknownIntervention() throws Exception {
		String testUrl = String.format("http://localhost:%s/agetacserver/intervention/test", testPort);
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
	public void testAddIntervention() throws Exception {
		// Construction de l'url de test
		String uniqueID = "test";
		String testUrl = String.format(
				"http://localhost:%s/agetacserver/intervention/%s", testPort,
				uniqueID);
		ClientResource client = new ClientResource(testUrl);

		// On construit la repr�sentation JSON de la ressource test�e
		/*
		 * FIXME This is the wrong way (I think). Normally, *no* client should put/add new
		 * interventions (why? see bellow). Clients play with what they get from the server. For example,
		 * if a client wants to add a new "Cible", it should call http://agetac.org/intervention/new
		 * which would return him a new Intervention (with a uniqueId generated).
		 * 
		 * The problem is that a client cannot generate a uniqueId for any model object. Imagine we
		 * have a bunch of clients, everyone on a different location, everyone trying to add a new 
		 * intervention with uniqueId = 25653224.
		 * 
		 * I'm not sure what I propose (above) is the best solution to this problem, but this is 
		 * something we need to think of. This is why we need a "communication protocol" that is
		 * supposed to solve this kind of problems.
		 *  - George
		 */
		Intervention test_inter1 = new Intervention("test");
		JsonRepresentation representation = new JsonRepresentation(test_inter1.toJSON());

		// On envoie (put) la ressource au serveur
		client.put(representation);

		
		// Maintenant ont essaye de r�cup�rer cette meme ressource
		JsonRepresentation representation2 = new JsonRepresentation(client.get());
		
		Intervention test_inter2 = new Intervention(representation2.getJsonObject());
		
		assertEquals("V�rification de l'id de la ressource r�cup�r�e", uniqueID, test_inter2.getUniqueID());

		// On le supprime.
		client.delete();

		// On s'assure qu'il n'�xiste plus.
		try {
			client.get();
			throw new Exception("L'intervention n'�xiste pas !");
		} catch (Exception e) { 
		}
	}

}
