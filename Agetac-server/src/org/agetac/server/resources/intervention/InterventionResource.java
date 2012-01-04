package org.agetac.server.resources.intervention;

import org.agetac.common.Intervention;
import org.agetac.server.db.Interventions;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

public class InterventionResource extends ServerResource {
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
	public Representation getContact() throws Exception {
		// Crée une representation JSON vide
		JsonRepresentation result = null;
		// Récupère l'identifiant unique de la ressource demandée.
		String uniqueID = (String) this.getRequestAttributes().get("uniqueID");
		// La recherche dans la base de données.
		Intervention intervention = Interventions.getInstance()
				.getIntervention(uniqueID);
		if (intervention == null) {
			// The requested contact was not found, so set the Status to
			// indicate this.
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
		} else {
			// The requested contact was found, so add the Contact's XML
			// representation to the response.
			result = new JsonRepresentation(intervention.toJson());
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
		Intervention intervention = new Intervention(jsonRepr.getJsonObject());
		// Add the Contact to our repository.
		Interventions.getInstance().addIntervention(intervention);
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
		Interventions.getInstance().deleteIntervention(uniqueID);
		return null;
	}
}
