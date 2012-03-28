package org.agetac.server;

import org.agetac.common.model.impl.Intervention;
import org.agetac.server.db.Interventions;
import org.agetac.server.resources.impl.ActionResource;
import org.agetac.server.resources.impl.CibleResource;
import org.agetac.server.resources.impl.DemandeResource;
import org.agetac.server.resources.impl.InterventionResource;
import org.agetac.server.resources.impl.MessageResource;
import org.agetac.server.resources.impl.SourceResource;
import org.agetac.server.resources.impl.VehiculeResource;
import org.restlet.*;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;
import org.restlet.security.MapVerifier;
import org.restlet.ext.crypto.DigestAuthenticator;

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
		Server server = new Server(Protocol.HTTP, port);
		component.getServers().add(server);
		// Create an application (this class).
		Application application = new AgetacServer();
		// Attach the application to the component with a defined contextRoot.
		String contextRoot = "/agetacserver";
		
		// Création du garde
		DigestAuthenticator guard = new DigestAuthenticator(application.getContext(), "TestRealm", "mySecretServerKey");
		
		// Instancie un Verifier de couple indentifiant/mdp basé sur une Map
		MapVerifier mapVerifier = new MapVerifier();
		// Charge un couple indentifiant/mdp
		mapVerifier.getLocalSecrets().put("login", "secret".toCharArray());
		guard.setWrappedVerifier(mapVerifier);
		
		//Associe le garde à l'application puis au component
		guard.setNext(application);
		component.getDefaultHost().attach(contextRoot, guard);
		
		server.getContext().getParameters().add("maxThreads", "512"); 
		server.getContext().getParameters().add("maxTotalConnections", "50");
	
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
		Interventions interventions = Interventions.getInstance();
		
		Intervention inter1 = new Intervention("1");
		/*Intervention inter2 = new Intervention("inter2");
		Intervention inter3 = new Intervention("inter3");

		inter1.getMessages().add(new org.agetac.model.impl.Message("1", "message1 inter1", "0102"));
		inter1.getMessages().add(new org.agetac.model.impl.Message("2", "message2 inter1", "0103"));
		inter1.getMessages().add(new org.agetac.model.impl.Message("3", "message3 inter1", "0104"));
		
		inter2.getMessages().add(new org.agetac.model.impl.Message("4", "message1 inter2", "0102"));
		inter2.getMessages().add(new org.agetac.model.impl.Message("5", "message2 inter2", "0103"));
		inter2.getMessages().add(new org.agetac.model.impl.Message("6", "message3 inter2", "0104"));
		*/

		interventions.addIntervention(inter1);
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
		
		server_router.attach("/intervention", InterventionResource.class);
		server_router.attach("/intervention/{interId}", InterventionResource.class);
		
		server_router.attach("/intervention/{interId}/message", MessageResource.class); // Tous les messages
		server_router.attach("/intervention/{interId}/message/{messageId}", MessageResource.class); // Un seul message

		server_router.attach("/intervention/{interId}/source", SourceResource.class); // Tous les source
		server_router.attach("/intervention/{interId}/source/{sourceId}", SourceResource.class); // Un seul source

		server_router.attach("/intervention/{interId}/cible", CibleResource.class); // Tous les cible
		server_router.attach("/intervention/{interId}/cible/{cibleId}", CibleResource.class); // Un seul cible
		
		server_router.attach("/intervention/{interId}/action", ActionResource.class); // Tous les cible
		server_router.attach("/intervention/{interId}/action/{actionId}", ActionResource.class); // Un seul cible

		server_router.attach("/intervention/{interId}/vehicule", VehiculeResource.class); // Tous les vehicule
		server_router.attach("/intervention/{interId}/vehicule/{vehiculeId}", VehiculeResource.class); // Un seul vehicule
		
		server_router.attach("/intervention/{interId}/demande", DemandeResource.class); // Tous les demande
		server_router.attach("/intervention/{interId}/demande/{demId}", DemandeResource.class); // Un seul demande
		
		
		/*
		//Sous routeur pour les interventions
		Router intervention_router = new Router(server_router.getContext());
		intervention_router.attach("/intervention", InterventionResource.class);
		intervention_router.attach("/intervention/{interId}", InterventionResource.class);
		
		
		Router message_router = new Router(intervention_router.getContext());
		message_router.attach("/intervention/{interId}/message", MessageResource.class); // Tous les messages
		message_router.attach("/intervention/{interId}/message/{messageId}", MessageResource.class); // Un seul message

		Router source_router = new Router(intervention_router.getContext());
		source_router.attach("/intervention/{interId}/source", SourceResource.class); // Tous les messages
		source_router.attach("/intervention/{interId}/source/{sourceId}", SourceResource.class); // Un seul message

		Router cible_router = new Router(intervention_router.getContext());
		cible_router.attach("/intervention/{interId}/cible", SourceResource.class); // Tous les messages
		cible_router.attach("/intervention/{interId}/cible/{sourceId}", SourceResource.class); // Un seul message

		Router vehicule_router = new Router(intervention_router.getContext());
		vehicule_router.attach("/intervention/{interId}/vehicule", VehiculeResource.class); // Tous les vehicules
		vehicule_router.attach("/intervention/{interId}/vehicule/{vehiculeId}", VehiculeResource.class); // Un seul vehicule
		
		Router demande_router = new Router(intervention_router.getContext());
		demande_router.attach("/intervention/{interId}/demande", DemandeResource.class); // Tous les messages
		demande_router.attach("/intervention/{interId}/demande/{demId}", DemandeResource.class); // Un seul message

		intervention_router.attach(message_router);
		intervention_router.attach(source_router);
		intervention_router.attach(cible_router);
		intervention_router.attach(vehicule_router);
		intervention_router.attach(demande_router);
		*/
		
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
		//server_router.attach(intervention_router);
		
		// Retourne le routeur.
		return server_router;
	}
}
