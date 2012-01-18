package org.agetac.server.resources.message;

import org.agetac.common.Message;
import org.agetac.server.db.Messages;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

public class MessageResource extends ServerResource {
	/**
	 * Retourne l'instance de l'message demander dans l'url
	 * 
	 * @return La representation de l'message demander ou
	 *         CLIENT_ERROR_NOT_ACCEPTABLE si l'id unique n'�xiste pas
	 * @throws Exception
	 *             En cas de probl�me de g�n�ration de repr�sentation. Ne
	 *             devrait pas arriver en pratique mais si c'est le cas, Restlet
	 *             met le bon code status.
	 */
	@Get
	public Representation getMessage() throws Exception {
		// Cr�e une representation JSON vide
		JsonRepresentation result = null;
		// R�cup�re l'identifiant unique de la ressource demand�e.
		String uniqueID = (String) this.getRequestAttributes().get("uniqueID");
		// System.out.println(uniqueID);
		// La recherche dans la base de donn�es.
		Message message = Messages.getInstance().getMessage(uniqueID);
		if (message == null) {
			// Ressource non-trouv�, envois du code status 406
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
		} else {
			// Ressource trouv�, envoie de la repr�sentation Json
			result = new JsonRepresentation(message.toJson());
			// Le code status par d�faut est 200 s'il n'est pas
		}
		// Retourne la repr�sentation, le code status indique au client si elle
		// est valide
		return result;
	}

	/**
	 * Ajoute le message transmit � la base de donn�es interne.
	 * 
	 * @param representation
	 *            La repr�sentation Json de l'message
	 * @return null.
	 * @throws Exception
	 *             En cas de probl�me de lecture de la representation.
	 */
	@Put
	public Representation putMessage(Representation representation)
			throws Exception {
		// R�cup�re la repr�sentation JSON de l'message
		JsonRepresentation jsonRepr = new JsonRepresentation(representation);
		// Transforme la representation en objet java
		Message message = new Message(jsonRepr.getJsonObject());
		// Ajoute l'message a la base de donn�e
		Messages.getInstance().addMessage(message);
		// Pas besoin de retourner de repr�sentation au client
		return null;
	}

	/**
	 * Supprime le message identifi� de la base de donn�es
	 * 
	 * @return null.
	 */
	@Delete
	public Representation deleteMessage() {
		// R�cup�re l'id dans l'url
		String uniqueID = (String) this.getRequestAttributes().get("uniqueID");
		// On s'assure qu'il n'est plus pr�sent en base de donn�es
		Messages.getInstance().deleteMessage(uniqueID);
		return null;
	}
}
