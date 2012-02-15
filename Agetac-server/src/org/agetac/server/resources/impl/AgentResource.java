package org.agetac.server.resources.impl;

import org.agetac.model.impl.Agent;
import org.agetac.server.db.Agents;
import org.agetac.server.resources.sign.IServerResource;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

public class AgentResource extends ServerResource implements IServerResource{
	/**
	 * Retourne l'instance de l'agent demander dans l'url
	 * 
	 * @return La representation de l'agent demander ou
	 *         CLIENT_ERROR_NOT_ACCEPTABLE si l'id unique n'éxiste pas
	 * @throws Exception
	 *             En cas de problème de génération de représentation. Ne
	 *             devrait pas arriver en pratique mais si c'est le cas, Restlet
	 *             met le bon code status.
	 */
	@Get
	public Representation getResource() throws Exception {
		// Crée une representation JSON vide
		JsonRepresentation result = null;
		// Récupère l'identifiant unique de la ressource demandée.
		String uniqueID = (String) this.getRequestAttributes().get("uniqueID");
		// System.out.println(uniqueID);
		// La recherche dans la base de données.
		Agent agent = Agents.getInstance().getAgent(uniqueID);
		if (agent == null) {
			// Ressource non-trouvé, envois du code status 406
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
		} else {
			// Ressource trouvé, envoie de la représentation Json
			result = new JsonRepresentation(agent.toJSON());
			// Le code status par défaut est 200 s'il n'est pas
		}
		// Retourne la représentation, le code status indique au client si elle
		// est valide
		return result;
	}

	/**
	 * Ajoute l'agent transmit à la base de données interne.
	 * 
	 * @param representation
	 *           La représentation Json de l'agent
	 * @return null.
	 * @throws Exception
	 *             En cas de problème de lecture de la representation.
	 */
	@Put
	public Representation putResource(Representation representation)
			throws Exception {
		// Récupère la représentation JSON de l'agent
		JsonRepresentation jsonRepr = new JsonRepresentation(representation);
		// Transforme la representation en objet java
		Agent agent = new Agent(jsonRepr.getJsonObject());
		// Ajoute l'agent a la base de donnée
		Agents.getInstance().addAgent(agent);
		// Pas besoin de retourner de représentation au client
		return null;
	}

	/**
	 * Supprime l'agent identifié de la base de données
	 * 
	 * @return null.
	 */
	@Delete
	public Representation deleteResource() {
		// Récupère l'id dans l'url
		String uniqueID = (String) this.getRequestAttributes().get("uniqueID");
		// On s'assure qu'il n'est plus présent en base de données
		Agents.getInstance().deleteAgent(uniqueID);
		return null;
	}

	@Override
	public Representation postResource(Representation representation)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
