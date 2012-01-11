package org.agetac.server.resources.agent;

import org.agetac.common.Agent;
import org.agetac.server.db.Agents;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

public class AgentResource extends ServerResource {
	/**
	 * Returns the Contact instance requested by the URL.
	 * 
	 * @return The XML representation of the contact, or
	 *         CLIENT_ERROR_NOT_ACCEPTABLE if the unique ID is not present.
	 * @throws Exception
	 *             If problems occur making the representation. Shouldn't occur
	 *             in practice but if it does, Restlet will set the Status code.
	 */
	@Get
	public Representation getAgent() throws Exception {
		// Crée une representation JSON vide
		JsonRepresentation result = null;
		// Récupère l'identifiant unique de la ressource demandée.
		String uniqueID = (String) this.getRequestAttributes().get("uniqueID");
		System.out.println(uniqueID);
		// La recherche dans la base de données.
		Agent agent = Agents.getInstance().getAgent(uniqueID);
		if (agent == null) {
			// The requested contact was not found, so set the Status to
			// indicate this.
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
		} else {
			// The requested contact was found, so add the Contact's XML
			// representation to the response.
			result = new JsonRepresentation(agent.toJson());
			// Status code defaults to 200 if we don't set it.
		}
		// Return the representation. The Status code tells the client if the
		// representation is valid.
		return result;
	}

	/**
	 * Adds the passed Contact to our internal database of Contacts.
	 * 
	 * @param representation
	 *            The XML representation of the new Contact to add.
	 * @return null.
	 * @throws Exception
	 *             If problems occur unpacking the representation.
	 */
	@Put
	public Representation putContact(Representation representation)
			throws Exception {
		// Get the XML representation of the Contact.
		JsonRepresentation jsonRepr = new JsonRepresentation(representation);
		// Convert the XML representation to the Java representation.
		Agent agent = new Agent(jsonRepr.getJsonObject());
		// Add the Contact to our repository.
		Agents.getInstance().addAgent(agent);
		// No need to return a representation to the client.
		return null;
	}

	/**
	 * Deletes the unique ID from the internal database.
	 * 
	 * @return null.
	 */
	@Delete
	public Representation deleteContact() {
		// Get the requested Contact ID from the URL.
		String uniqueID = (String) this.getRequestAttributes().get("uniqueID");
		// Make sure it is no longer present in the Contacts database.
		Agents.getInstance().deleteAgent(uniqueID);
		return null;
	}
}
