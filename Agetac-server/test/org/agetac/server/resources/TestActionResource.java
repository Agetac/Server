		package org.agetac.server.resources;
		
		import static org.junit.Assert.*;
		
		import org.agetac.common.model.impl.Action;
		import org.agetac.common.model.impl.Intervention;
		import org.agetac.server.AgetacServer;
import org.agetac.server.resources.impl.CaserneResourceTest;
		import org.junit.BeforeClass;
		import org.junit.Test;
		import org.restlet.ext.json.JsonRepresentation;
		import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
		
		public class TestActionResource {
			
			/** Le port utilisé pour les tests. */
			private static int testPort = 8112;
			private ClientResource client;
		
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
			public void testUnknownAction() throws Exception {
				String testUrl = String.format("http://localhost:%s/agetacserver/action/test", testPort);
				ClientResource client = new ClientResource(testUrl);
				client.get();	
			}
		
			@Test
			public void testAction() throws Exception {
				// Construction de l'url de test
		
				String testUrl = String.format("http://localhost:%s/agetacserver/action", testPort);
				client = new ClientResource(testUrl);
				
				Action action1 = new Action();
				JsonRepresentation jsonAction1 = new JsonRepresentation(client.put(new JsonRepresentation(action1.toJSON())));
				action1 = new Action(jsonAction1.getJsonObject());
		
				//On met a jour sur le serveur
		
				testUrl = String.format("http://localhost:%s/agetacserver/action/%s", testPort, action1.getUniqueID());
				client = new ClientResource(testUrl);
				client.post(new JsonRepresentation(action1.toJSON()));
						
				// Maintenant ont essaye de récupérer cette meme action
				JsonRepresentation jsonAction2 = new JsonRepresentation(client.get());
				Action action2 = new Action(jsonAction2.getJsonObject());
						
				assertEquals("Vérification de l'id de l'action récupérée", action1.getUniqueID(), action2.getUniqueID());
					
				// On la supprime.
				client.delete();
				
				// On s'assure qu'il n'éxiste plus.
				try {
						client.get();
						throw new Exception("L'intervention n'éxiste pas !");
				} catch (Exception e) { 
				}
			}
		
		
				}