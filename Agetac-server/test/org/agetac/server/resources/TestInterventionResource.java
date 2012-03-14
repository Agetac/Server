package org.agetac.server.resources;

import static org.junit.Assert.*;

import org.agetac.common.model.impl.Intervention;
import org.agetac.common.model.impl.Message;
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
	public void testIntervention() throws Exception {
		// Construction de l'url de test
		String uniqueID = "new";
		String testUrl = String.format("http://localhost:%s/agetacserver/intervention/%s", testPort, uniqueID);
		ClientResource client = new ClientResource(testUrl);

		// On construit la représentation JSON de la ressource testée
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
		 *  
		 * Done
		 *  - Clément
		 */


		// On demande (put) une nouvelle ressource au serveur
		JsonRepresentation jsonrepr1 = new JsonRepresentation(client.put(null));
		
		// On récupère et modifie cette intervention
		Intervention inter1 = new Intervention(jsonrepr1.getJsonObject());
		inter1.getMessages().add(new Message("1", "mon message", "0000"));
		
		//On met a jour sur le serveur
		uniqueID = inter1.getUniqueID();
		testUrl = String.format("http://localhost:%s/agetacserver/intervention/%s", testPort, uniqueID);
		client = new ClientResource(testUrl);
		client.post(new JsonRepresentation(inter1.toJSON()));
		
		
		// Maintenant ont essaye de récupérer cette meme ressource
		JsonRepresentation jsonrepr2 = new JsonRepresentation(client.get());
		Intervention inter2 = new Intervention(jsonrepr2.getJsonObject());
		
		assertEquals("Vérification de l'id de la ressource récupérée", uniqueID, inter2.getUniqueID());
		assertEquals("Vérification de la modification", 1, inter2.getMessages().size());
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
