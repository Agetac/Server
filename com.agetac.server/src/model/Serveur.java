package model;

import org.restlet.*;
import org.restlet.data.Protocol;

public class Serveur {

	
	private static Component serviceComponent;
	private static int port = 8181;
	private static String context = "agetac";
	private static Application app = new AgetacApp(context);
	
	public static void main(String[] args) throws Exception {
		
		// Cr�ation d'un composant RestLet
		serviceComponent = new Component();
		
		// Cr�ation d'un serveur fonctionnant avec HTTP sur le port 8181
		Server httpServer = new Server(Protocol.HTTP, port);
		
		// Ajout du serveur au composant RestLet
		serviceComponent.getServers().add(httpServer);
		
		// La racine de l'application app sera : localhost:8181/agetac
		serviceComponent.getDefaultHost().attach("/" + context, app );
		
		// D�marage du serveur
		serviceComponent.start();
		
	}
	
}
