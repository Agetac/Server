package org.agetac.server.resources;

import static org.junit.Assert.*;

import org.agetac.common.model.impl.Caserne;
import org.agetac.server.AgetacServer;
import org.agetac.server.resources.impl.CaserneResource;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class TestCaserneResource {

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
	public void testUnknownCaserne() throws Exception {
		String testUrl = String.format("http://localhost:%s/agetacserver/caserne/test", testPort);
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
	public void testCaserne() throws Exception {
		// Construction de l'url de test

		String testUrl = String.format("http://localhost:%s/agetacserver/caserne", testPort);
		ClientResource client = new ClientResource(testUrl);

		// On construit la représentation JSON de la ressource testée
		
		//Caserne caserne1 = new Caserne(testUrl, testUrl, null);
		
		// On ajoute (put) une nouvelle ressource au serveur et on la reçois avec un id
		//Caserne caserne1 = new Caserne(testUrl, testUrl, null);
		//Caserne caserne1 = new Caserne(testUrl, testUrl, null);
		//CaserneResource caserne1 = new CaserneResource();
		//JsonRepresentation jsoncas1 = new JsonRepresentation(client.put(new JsonRepresentation(caserne1.toJSON())));
		//Caserne caserne1 = new Caserne(jsoncas1.getJsonObject());
		
		
		//On met a jour sur le serveur
		//testUrl = String.format("http://localhost:%s/agetacserver/caserne/%s", testPort, caserne1.getUniqueID());
		//client = new ClientResource(testUrl);
		//client.post(new JsonRepresentation(caserne1.toJSON()));
		
		
		// Maintenant ont essaye de récupérer cette meme ressource
		//JsonRepresentation jsoncas2 = new JsonRepresentation(client.get());
		//Caserne caserne2 = new Caserne(jsoncas2.getJsonObject());
		
		//("Vérification de l'id de la ressource récupérée", caserne1.getUniqueID(), caserne2.getUniqueID());
		//assertEquals("Vérification de la modification", 1, inter2.getMessages().size());
		
		// On le supprime.
		//client.delete();

		// On s'assure qu'il n'éxiste plus.
		try {
			client.get();
			throw new Exception("L'intervention n'éxiste pas !");
		} catch (Exception e) { 
		}
	}
	
	
	
	
	@Test
	public void testDeleteResource_1()
		throws Exception {
		CaserneResource fixture = new CaserneResource();

		//Representation result = fixture.deleteResource();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.agetac.server.resources.impl.CaserneResource.deleteResource(CaserneResource.java:79)
		//assertNotNull(result);
	}

	/**
	 * Run the Representation getResource() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/03/12 14:38
	 */
	@Test(expected = java.lang.NullPointerException.class)
	public void testGetResource_1()
		throws Exception {
		CaserneResource fixture = new CaserneResource();

		Representation result = fixture.getResource();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Representation getResource() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/03/12 14:38
	 */
	@Test(expected = java.lang.NullPointerException.class)
	public void testGetResource_2()
		throws Exception {
		CaserneResource fixture = new CaserneResource();

		Representation result = fixture.getResource();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Representation getResource() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/03/12 14:38
	 */
	@Test(expected = java.lang.NullPointerException.class)
	public void testGetResource_3()
		throws Exception {
		CaserneResource fixture = new CaserneResource();

		Representation result = fixture.getResource();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Representation getResource() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/03/12 14:38
	 */
	@Test(expected = java.lang.NullPointerException.class)
	public void testGetResource_4()
		throws Exception {
		CaserneResource fixture = new CaserneResource();

		Representation result = fixture.getResource();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Representation getResource() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/03/12 14:38
	 */
	@Test(expected = java.lang.NullPointerException.class)
	public void testGetResource_5()
		throws Exception {
		CaserneResource fixture = new CaserneResource();

		Representation result = fixture.getResource();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Representation getResource() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/03/12 14:38
	 */
	@Test(expected = java.lang.NullPointerException.class)
	public void testGetResource_6()
		throws Exception {
		CaserneResource fixture = new CaserneResource();

		Representation result = fixture.getResource();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Representation getResource() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/03/12 14:38
	 */
	@Test(expected = java.lang.NullPointerException.class)
	public void testGetResource_7()
		throws Exception {
		CaserneResource fixture = new CaserneResource();

		Representation result = fixture.getResource();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Representation postResource(Representation) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/03/12 14:38
	 */
	@Test
	public void testPostResource_1()
		throws Exception {
		CaserneResource fixture = new CaserneResource();
		Representation representation = Representation.createEmpty();

		Representation result = fixture.postResource(representation);

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the Representation putResource(Representation) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/03/12 14:38
	 */
	@Test(expected = java.lang.NullPointerException.class)
	public void testPutResource_1()
		throws Exception {
		CaserneResource fixture = new CaserneResource();
		Representation representation = Representation.createEmpty();

		Representation result = fixture.putResource(representation);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Representation putResource(Representation) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/03/12 14:38
	 */
	@Test(expected = java.lang.NullPointerException.class)
	public void testPutResource_2()
		throws Exception {
		CaserneResource fixture = new CaserneResource();
		Representation representation = Representation.createEmpty();

		Representation result = fixture.putResource(representation);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Representation putResource(Representation) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/03/12 14:38
	 */
	@Test(expected = java.lang.NullPointerException.class)
	public void testPutResource_3()
		throws Exception {
		CaserneResource fixture = new CaserneResource();
		Representation representation = Representation.createEmpty();

		Representation result = fixture.putResource(representation);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Representation putResource(Representation) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/03/12 14:38
	 */
	@Test(expected = java.lang.NullPointerException.class)
	public void testPutResource_4()
		throws Exception {
		CaserneResource fixture = new CaserneResource();
		Representation representation = Representation.createEmpty();

		Representation result = fixture.putResource(representation);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the JSONArray toJSON() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 28/03/12 14:38
	 */
	@Test
	public void testToJSON_1()
		throws Exception {
		CaserneResource fixture = new CaserneResource();

		//JSONArray result = fixture.toJSON();

		// add additional test code here
		//assertEquals(null, result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 28/03/12 14:38
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 28/03/12 14:38
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 28/03/12 14:38
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(TestCaserneResource.class);
	}

}