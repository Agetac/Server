package org.agetac.server;

import org.agetac.server.resources.agent.AgentResource;
import org.agetac.server.resources.intervention.InterventionResource;
import org.agetac.server.resources.message.MessageResource;
import org.restlet.*;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

public class AgetacServer extends Application {

	/**
	 * D�marre un serveur sur le port sp�cifi�. La racine sera "agetacserver".
	 * 
	 * @param port Le port sur lequel le serveur doit fonctionner
	 * @throws Exception Si des probl�me surviennent au lancement du serveur.
	 */
	public static void runServer(int port) throws Exception {
		// Create a component.
		Component component = new Component();
		component.getServers().add(Protocol.HTTP, port);
		// Create an application (this class).
		Application application = new AgetacServer();
		// Attach the application to the component with a defined contextRoot.
		String contextRoot = "/agetacserver";
		component.getDefaultHost().attach(contextRoot, application);
		component.start();
	}

	/**
	 * Cette m�thode main d�marre un serveur sur le port 8111.
	 * 
	 * @param args
	 * @throws Exception Si un probl�me survient.
	 */
	public static void main(String[] args) throws Exception {
		runServer(8111);
	}

	/**
	 * Sp�cifie le routage des URIs et leurs ressources associ�.
	 * 
	 * @return Un routeur qui associe les URIs et les ressources
	 */
	@Override
	public Restlet createInboundRoot() {
		// Cr�e un routeur restlet.
		Router router = new Router(getContext());
		// Attache les ressources au routeur.
		router.attach("/intervention/{uniqueID}", InterventionResource.class);
		router.attach("/agent/{uniqueID}", AgentResource.class);
		router.attach("/message/{uniqueID}", MessageResource.class);
		// Retourne le routeur.
		return router;
	}
}
