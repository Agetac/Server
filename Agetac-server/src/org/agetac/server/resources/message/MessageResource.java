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
	 *         CLIENT_ERROR_NOT_ACCEPTABLE si l'id unique n'éxiste pas
	 * @throws Exception
	 *             En cas de problème de génération de représentation. Ne
	 *             devrait pas arriver en pratique mais si c'est le cas, Restlet
	 *             met le bon code status.
	 */
	@Get
	public Representation getMessage() throws Exception {
		// Crée une representation JSON vide
		JsonRepresentation result = null;
		// Récupère l'identifiant unique de la ressource demandée.
		String uniqueID = (String) this.getRequestAttributes().get("uniqueID");
		// System.out.println(uniqueID);
		// La recherche dans la base de données.
		Message message = Messages.getInstance().getMessage(uniqueID);
		if (message == null) {
			// Ressource non-trouvé, envois du code status 406
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
		} else {
			// Ressource trouvé, envoie de la représentation Json
			result = new JsonRepresentation(message.toJson());
			// Le code status par défaut est 200 s'il n'est pas
		}
		// Retourne la représentation, le code status indique au client si elle
		// est valide
		return result;
	}

	/**
	 * Ajoute le message transmit à la base de données interne.
	 * 
	 * @param representation
	 *            La représentation Json de l'message
	 * @return null.
	 * @throws Exception
	 *             En cas de problème de lecture de la representation.
	 */
	@Put
	public Representation putMessage(Representation representation)
			throws Exception {
		// Récupère la représentation JSON de l'message
		JsonRepresentation jsonRepr = new JsonRepresentation(representation);
		// Transforme la representation en objet java
		Message message = new Message(jsonRepr.getJsonObject());
		// Ajoute l'message a la base de donnée
		Messages.getInstance().addMessage(message);
		// Pas besoin de retourner de représentation au client
		return null;
	}

	/**
	 * Supprime le message identifié de la base de données
	 * 
	 * @return null.
	 */
	@Delete
	public Representation deleteMessage() {
		// Récupère l'id dans l'url
		String uniqueID = (String) this.getRequestAttributes().get("uniqueID");
		// On s'assure qu'il n'est plus présent en base de données
		Messages.getInstance().deleteMessage(uniqueID);
		return null;
	}
}
