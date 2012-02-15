package org.agetac.server;

import org.agetac.model.impl.Intervention;
import org.agetac.server.db.Interventions;
import org.agetac.server.resources.impl.AgentResource;
import org.agetac.server.resources.impl.InterventionResource;
import org.agetac.server.resources.impl.MessageResource;
import org.agetac.server.resources.impl.SourceResource;
import org.agetac.server.resources.impl.VehiculeResource;
import org.restlet.*;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

public class AgetacServer extends Application {

	/**
	 * Démarre un serveur sur le port spécifié. La racine sera "agetacserver".
	 * 
	 * @param port Le port sur lequel le serveur doit fonctionner
	 * @throws Exception Si des problème surviennent au lancement du serveur.
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
	 * Cette méthode main démarre un serveur sur le port 8111.
	 * 
	 * @param args
	 * @throws Exception Si un problème survient.
	 */
	public static void main(String[] args) throws Exception {
		runServer(8112);
		/*Interventions interventions = Interventions.getInstance();
		
		Intervention inter1 = new Intervention("inter1");
		Intervention inter2 = new Intervention("inter2");
		Intervention inter3 = new Intervention("inter3");

		inter1.getMessages().add(new org.agetac.model.impl.Message("1", "message1 inter1", "0102"));
		inter1.getMessages().add(new org.agetac.model.impl.Message("2", "message2 inter1", "0103"));
		inter1.getMessages().add(new org.agetac.model.impl.Message("3", "message3 inter1", "0104"));
		
		inter2.getMessages().add(new org.agetac.model.impl.Message("4", "message1 inter2", "0102"));
		inter2.getMessages().add(new org.agetac.model.impl.Message("5", "message2 inter2", "0103"));
		inter2.getMessages().add(new org.agetac.model.impl.Message("6", "message3 inter2", "0104"));
		

		interventions.addIntervention(inter1);
		interventions.addIntervention(inter2);*/
	}

	/**
	 * Spécifie le routage des URIs et leurs ressources associé.
	 * 
	 * @return Un routeur qui associe les URIs et les ressources
	 */
	@Override
	public Restlet createInboundRoot() {
		// Crée un routeur restlet.
		Router router = new Router(getContext());
		// Attache les ressources au routeur.
		router.attach("/intervention/{interId}", InterventionResource.class);
		router.attach("/intervention/{interId}/message", MessageResource.class); // Tous les messages
		router.attach("/intervention/{interId}/message/{messageId}", MessageResource.class); // Un seul message
		
		router.attach("/intervention/{interId}/source", SourceResource.class); // Tous les messages
		router.attach("/intervention/{interId}/source/{sourceId}", SourceResource.class); // Un seul message
		
		router.attach("/intervention/{interId}/vehicule", VehiculeResource.class); // Tous les vehicules
		router.attach("/intervention/{interId}/vehicule/{vehiculeId}", VehiculeResource.class); // Un seul vehicule
		
		router.attach("/intervention/{interId}/agent/{agentId}", AgentResource.class);
		// Retourne le routeur.
		return router;
	}
}
