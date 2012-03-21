package org.agetac.server;

import org.agetac.server.db.DbUtils;
import org.agetac.server.resources.impl.*;
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
		DbUtils.populateTestDb();
		//Interventions interventions = Interventions.getInstance();
		
		//Intervention inter1 = new Intervention("1");
		/*Intervention inter2 = new Intervention("inter2");
		Intervention inter3 = new Intervention("inter3");

		inter1.getMessages().add(new org.agetac.model.impl.Message("1", "message1 inter1", "0102"));
		inter1.getMessages().add(new org.agetac.model.impl.Message("2", "message2 inter1", "0103"));
		inter1.getMessages().add(new org.agetac.model.impl.Message("3", "message3 inter1", "0104"));
		
		inter2.getMessages().add(new org.agetac.model.impl.Message("4", "message1 inter2", "0102"));
		inter2.getMessages().add(new org.agetac.model.impl.Message("5", "message2 inter2", "0103"));
		inter2.getMessages().add(new org.agetac.model.impl.Message("6", "message3 inter2", "0104"));
		*/

		//interventions.addIntervention(inter1);
		/*interventions.addIntervention(inter2);*/
	}

	/**
	 * Spécifie le routage des URIs et leurs ressources associé.
	 * 
	 * @return Un routeur qui associe les URIs et les ressources
	 */
	@Override
	public Restlet createInboundRoot() {
		// Crée un routeur restlet.
		Router server_router = new Router(getContext());
		
		// A collection of X is different from X.
		// http://restlet-discuss.1400322.n2.nabble.com/Is-Multiple-Get-and-Put-annotations-Possible-td5390580.html
		
		//Sous routeur pour les interventions
		Router intervention_router = new Router(server_router.getContext());
		intervention_router.attach("/intervention", InterventionsResource.class);
		intervention_router.attach("/intervention/{uid}", InterventionResource.class);
		
		
		Router message_router = new Router(intervention_router.getContext());
		message_router.attach("/intervention/{interId}/message", MessagesResource.class); // Tous les messages
		message_router.attach("/intervention/{interId}/message/{uid}", MessageResource.class); // Un seul message

		Router source_router = new Router(intervention_router.getContext());
		source_router.attach("/intervention/{interId}/source", SourcesResource.class); // Tous les messages
		source_router.attach("/intervention/{interId}/source/{uid}", SourceResource.class); // Un seul message

		Router cible_router = new Router(intervention_router.getContext());
		cible_router.attach("/intervention/{interId}/cible", CiblesResource.class); // Tous les messages
		cible_router.attach("/intervention/{interId}/cible/{uid}", CibleResource.class); // Un seul message

		Router vehicule_router = new Router(intervention_router.getContext());
		vehicule_router.attach("/intervention/{interId}/vehicule", VehiculesResource.class); // Tous les vehicules
		vehicule_router.attach("/intervention/{interId}/vehicule/{uid}", VehiculeResource.class); // Un seul vehicule

		intervention_router.attach(message_router);
		intervention_router.attach(source_router);
		intervention_router.attach(cible_router);
		
		Router login_router = new Router(intervention_router.getContext());
		login_router.attach("/login", MessageResource.class);
		
		// Authentification (En cours)
		/*
		DigestAuthenticator guard = new DigestAuthenticator(intervention_router.getContext(), "TestRealm", "mySecretServerKey");
		
		// Instantiates a Verifier of identifier/secret couples based on a simple Map.
		MapVerifier mapVerifier = new MapVerifier();
		// Load a single static login/secret pair.
		mapVerifier.getLocalSecrets().put("login", "secret".toCharArray());
		guard.setWrappedVerifier(mapVerifier);

		guard.setNext(intervention_router);
		
		// Authentification (En cours)
		
		server_router.attach(guard);
		*/
		server_router.attach(intervention_router);
		server_router.attach(login_router);
		
		// Retourne le routeur.
		return server_router;
	}
}
