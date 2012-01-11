package model;

import org.restlet.*;
import org.restlet.data.Protocol;

public class Serveur {

	
	private static Component serviceComponent;
	private static int port = 8181;
	private static String context = "agetac";
	private static Application app = new AgetacApp(context);
	
	
	public static void main(String[] args) throws Exception {
		
		
		
		serviceComponent = new Component();
		Server httpServer = new Server(Protocol.HTTP, port);
		
		
		serviceComponent.getServers().add(httpServer);
		serviceComponent.getDefaultHost().attach("/" + context, app );
		serviceComponent.start();
		
	}

	
	
	
	
	
}
