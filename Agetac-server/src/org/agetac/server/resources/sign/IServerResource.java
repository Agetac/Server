package org.agetac.server.resources.sign;

import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

public interface IServerResource {
	
	/**
	 * Retourne l'instance de la ressource demandé dans l'url.
	 * 
	 * @return La representation de la ressource demander ou
	 *         CLIENT_ERROR_NOT_ACCEPTABLE si l'id unique n'éxiste pas
	 * @throws Exception
	 *             En cas de problème de génération de représentation. Ne
	 *             devrait pas arriver en pratique mais si c'est le cas, Restlet
	 *             met le bon code status.
	 */
	@Get
	public Representation getResource() throws Exception;
	
	/**
	 * Ajoute une nouvelle ressource à la base de données interne et renvoie la représentation.
	 * 
	 * @param representation La représentation Json de la ressource.
	 * @return null
	 * @throws Exception
	 *             En cas de problème de lecture de la representation.
	 */
	@Put
	public Representation putResource(Representation representation) throws Exception;
	
	
	/**
	 * Met à jour la ressource identifiée dans la base de données grâce à la représentation fournie.
	 * @param representation La représentation Json de la ressource.
	 * @return null
	 * @throws Exception
	 */
	@Post
	public Representation postResource(Representation representation) throws Exception;
	
	/**
	 * Supprime la ressource identifiée de la base de données.
	 * 
	 * @return null.
	 */
	@Delete
	public Representation deleteResource();
	
}
