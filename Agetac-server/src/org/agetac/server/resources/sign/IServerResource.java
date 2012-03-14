package org.agetac.server.resources.sign;

import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

public interface IServerResource {
	
	/**
	 * Retourne l'instance de la ressource demand� dans l'url.
	 * 
	 * @return La representation de la ressource demander ou
	 *         CLIENT_ERROR_NOT_ACCEPTABLE si l'id unique n'�xiste pas
	 * @throws Exception
	 *             En cas de probl�me de g�n�ration de repr�sentation. Ne
	 *             devrait pas arriver en pratique mais si c'est le cas, Restlet
	 *             met le bon code status.
	 */
	@Get
	public Representation getResource() throws Exception;
	
	/**
	 * Ajoute une nouvelle ressource � la base de donn�es interne et renvoie la repr�sentation.
	 * 
	 * @param representation La repr�sentation Json de la ressource.
	 * @return null
	 * @throws Exception
	 *             En cas de probl�me de lecture de la representation.
	 */
	@Put
	public Representation putResource(Representation representation) throws Exception;
	
	
	/**
	 * Met � jour la ressource identifi�e dans la base de donn�es gr�ce � la repr�sentation fournie.
	 * @param representation La repr�sentation Json de la ressource.
	 * @return null
	 * @throws Exception
	 */
	@Post
	public Representation postResource(Representation representation) throws Exception;
	
	/**
	 * Supprime la ressource identifi�e de la base de donn�es.
	 * 
	 * @return null.
	 */
	@Delete
	public Representation deleteResource();
	
}
