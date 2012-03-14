package org.agetac.client;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import org.agetac.client.api.ServerConnection;
import org.agetac.common.exception.BadResponseException;
import org.agetac.common.exception.InvalidJSONException;
import org.agetac.common.model.impl.Intervention;
import org.agetac.server.AgetacServer;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class TestServerConnection {

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
	 * Test qu'on ne peut pas récupérer de ressource inconnue sans lancer d'exception.
	 * @throws Throwable 
	 */
	@Test(expected = BadResponseException.class)
	public void testGetUnknownRessource() throws BadResponseException {
		
		ServerConnection servCon = new ServerConnection("localhost", ""+testPort, "agetacserver");
		Representation repr = servCon.getResource("ressource_non_existante", "testId");
		
	}
	
	/**
	 * Test qu'on ne peut pas envoyer de ressource inconnue sans lancer d'exception.
	 * 
	 * @throws Exception
	 *             Si pas d'exception, on a un problème.
	 */
	@Test(expected = BadResponseException.class)
	public void testPutUnknownRessource() throws BadResponseException {
		
		ServerConnection servCon = new ServerConnection("localhost", ""+testPort, "agetacserver");
		servCon.putResource("ressource_non_existante", "testId", new JsonRepresentation("{}"));
		
	}
	
	/**
	 * Test qu'on ne peut pas mettre a jour de ressource inconnue sans lancer d'exception.
	 * 
	 * @throws Exception
	 *             Si pas d'exception, on a un problème.
	 */
	@Test(expected = BadResponseException.class)
	public void testPostUnknownRessource() throws BadResponseException {
		
		ServerConnection servCon = new ServerConnection("localhost", ""+testPort, "agetacserver");
		servCon.postResource("ressource_non_existante", "testId", new JsonRepresentation("{}"));
		
	}
	
	/**
	 * Test qu'on ne peut pas supprimer de ressource inconnue sans lancer d'exception.
	 * 
	 * @throws Exception
	 *             Si pas d'exception, on a un problème.
	 */
	@Test(expected = BadResponseException.class)
	public void testDeleteUnknownRessource() throws BadResponseException {
		
		ServerConnection servCon = new ServerConnection("localhost", ""+testPort, "agetacserver");
		servCon.deleteResource("ressource_non_existante", "testId");
		
	}
	
	
	
	/**
	 * Test l'envoie d'une ressource
	 * 
	 * @throws BadResponseException 
	 * @throws JSONException 
	 */
	@Test
	public void testPutRessource() throws BadResponseException, JSONException{
		Intervention inter = new Intervention();
		ServerConnection servCon = new ServerConnection("localhost", ""+testPort, "agetacserver");
		servCon.putResource("intervention", "test", new JsonRepresentation(inter.toJSON()));
	}
	
	/**
	 * Test la récupération d'une ressource
	 * 
	 * @throws BadResponseException 
	 * @throws JSONException 
	 * @throws IOException 
	 * @throws InvalidJSONException 
	 */
/*	@Test
	public void testGetRessource() throws BadResponseException, IOException, InvalidJSONException, JSONException{
		Intervention inter;
		ServerConnection servCon = new ServerConnection("localhost", ""+testPort, "agetacserver");
		Representation repr = servCon.getResource("intervention", "test");
		JsonRepresentation jsonrepr = new JsonRepresentation(repr);
		inter = new Intervention(jsonrepr.getJsonObject());
		
		assertEquals("Vérification de l'id de la ressource récupérée", inter.getUniqueID(), "test");
	}
	*/
	
	/**
	 * Test la modification d'une ressource
	 * 
	 * @throws BadResponseException 
	 * @throws JSONException 
	 */
	@Test
	public void testPostRessource(){
		assertEquals("Vérification de l'id de la ressource récupérée", false, true);
		assertEquals("Vérification de l'attribut modifié", false, true);
	}
	
	/**
	 * Test la suppression d'une ressource
	 * 
	 * @throws BadResponseException 
	 * @throws JSONException 
	 */
	@Test
	public void testDeleteRessource(){
		assertEquals("", false, true);
	}
}
