package org.agetac.server;

import java.io.File;

import org.agetac.server.resources.ActionResourceImpl;
import org.agetac.server.resources.ActionsResourceImpl;
import org.agetac.server.resources.InterventionResourceImpl;
import org.agetac.server.resources.InterventionsResourceImpl;
import org.agetac.server.resources.MessageResourceImpl;
import org.agetac.server.resources.MessagesResourceImpl;
import org.agetac.server.resources.SourceResourceImpl;
import org.agetac.server.resources.SourcesResourceImpl;
import org.agetac.server.resources.TargetResourceImpl;
import org.agetac.server.resources.TargetsResourceImpl;
import org.agetac.server.resources.VehicleDemandResourceImpl;
import org.agetac.server.resources.VehicleDemandsResourceImpl;
import org.agetac.server.resources.VehicleResourceImpl;
import org.agetac.server.resources.VehiclesResourceImpl;
import org.agetac.server.resources.VictimResourceImpl;
import org.agetac.server.resources.VictimsResourceImpl;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

public class Server {
	
	private static Component component;
	
	public static void main(String[] args) throws Exception {
		deleteDir(new File("db"));
		startServer(8888);
	}

	public static void startServer(int port) throws Exception {
		// Start the server.
		component = new Component();
		component.getServers().add(Protocol.HTTP, port);

		component.getDefaultHost().attach(new Application() {
			@Override
			public Restlet createInboundRoot() {

				Router router = new Router();
				router.attach("/intervention/{interId}",InterventionResourceImpl.class);
				router.attach("/intervention", InterventionResourceImpl.class);
				router.attach("/interventions", InterventionsResourceImpl.class);
				router.attach("/intervention/{interId}/messages",MessagesResourceImpl.class);
				router.attach("/intervention/{interId}/targets",TargetsResourceImpl.class);
				router.attach("/intervention/{interId}/sources",SourcesResourceImpl.class);
				router.attach("/intervention/{interId}/actions",ActionsResourceImpl.class);
				router.attach("/intervention/{interId}/victims",VictimsResourceImpl.class);
				router.attach("/intervention/{interId}/vehicles",VehiclesResourceImpl.class);
				router.attach("/intervention/{interId}/vehicledemands",VehicleDemandsResourceImpl.class);

				router.attach("/vehicledemand/{vdId}",VehicleDemandResourceImpl.class);
				router.attach("/intervention/{interId}/vehicledemand",VehicleDemandResourceImpl.class);

				router.attach("/message/{msgId}", MessageResourceImpl.class);
				router.attach("/intervention/{interId}/message",MessageResourceImpl.class);

				router.attach("/target/{targetId}", TargetResourceImpl.class);
				router.attach("/intervention/{interId}/target",TargetResourceImpl.class);

				router.attach("/source/{sourceId}", SourceResourceImpl.class);
				router.attach("/intervention/{interId}/source",SourceResourceImpl.class);

				router.attach("/action/{actionId}", ActionResourceImpl.class);
				router.attach("/intervention/{interId}/action",ActionResourceImpl.class);

				router.attach("/vehicle/{vehicleId}", VehicleResourceImpl.class);
				router.attach("/intervention/{interId}/vehicle",VehicleResourceImpl.class);

				router.attach("/victim/{victimId}", VictimResourceImpl.class);
				router.attach("/intervention/{interId}/victim",VictimResourceImpl.class);

				return router;
			}
		});

		component.start();
	}

	public static void stopServer() throws Exception {
		component.stop();
	}
	
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}

		// The directory is now empty so delete it
		return dir.delete();
	}
}
