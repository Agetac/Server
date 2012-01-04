package org.agetac.server.resources.agent;

import static org.junit.Assert.*;

import org.agetac.common.Agent;
import org.agetac.common.Aptitude;
import org.agetac.server.AgetacServer;
import org.junit.BeforeClass;
import org.junit.Test;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;


public class TestAgentResource {

	/** The port used for testing. */
	private static int testPort = 8112;

	/**
	 * Start up a test server before testing any of the operations on this
	 * resource.
	 * 
	 * @throws Exception
	 *             If problems occur starting up the server.
	 */
	@BeforeClass
	public static void startServer() throws Exception {
		AgetacServer.runServer(testPort);
	}

	/**
	 * Tests that we cannot retrieve an unknown Agent ID without throwing an
	 * exception.
	 * 
	 * @throws Exception
	 *             If no exception, Houston we have a problem.
	 */
	@Test(expected = ResourceException.class)
	public void testUnknownAgent() throws Exception {
		String testUrl = String.format(
				"http://localhost:%s/agetacserver/agent/foo", testPort);
		ClientResource client = new ClientResource(testUrl);
		client.get();
	}

	/**
	 * Test the cycle of putting a new Agent on the server, retrieving it,
	 * then deleting it.
	 * 
	 * @throws Exception
	 *             If problems occur.
	 */
	@Test
	public void testAddAgent() throws Exception {
		// Construct the URL to test.
		String uniqueID = "np";
		String testUrl = String.format(
				"http://localhost:%s/agetacserver/agent/%s", testPort,
				uniqueID);
		ClientResource client = new ClientResource(testUrl);

		// Construct the payload: an XML representation of a Agent.
		Agent agent = new Agent(uniqueID, "Noel", new Aptitude("CDG"), null, null);
		JsonRepresentation representation = new JsonRepresentation(agent.toJson());

		// Now put the Agent to the server.
		client.put(representation);

		// Let's now try to retrieve the Agent instance we just put on the
		// server.
		JsonRepresentation representation2 = new JsonRepresentation(client.get());
		Agent agent2 = new Agent(representation2.getJsonObject());
		assertEquals("Checking retrieved agent's ID", uniqueID,
				agent2.getUniqueID());

		// Now let's get rid of the sucker.
		client.delete();

		// Make sure it's really gone.
		try {
			client.get();
			throw new Exception("Eek! We got a deleted Agent!");
		} catch (Exception e) { // NOPMD
			// It's all G.
		}
	}
}
