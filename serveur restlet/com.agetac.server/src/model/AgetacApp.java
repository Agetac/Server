package model;

import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.Application;
//import org.restlet.Context;
import org.restlet.routing.Router;

public class AgetacApp extends Application{

	public static final String DEFAULT_ROWS = "1";
	private final String contextRoot;
	public SDIS sdis;
	
	
	public AgetacApp(String tld)
	{
		super();
		this.contextRoot = tld;
		sdis = new SDIS();
	}

	public String getContextRoot() { return contextRoot; }

	//TODO Confirm this works in GAE
	public AgetacApp(Context ctx)
	{
		super(ctx);
		this.contextRoot = (String) ctx.getAttributes().get("org.restlet.ext.servlet.offsetPath");
	}


	public synchronized Restlet createInboundRoot()
	{
		this.getTunnelService().setExtensionsTunnel(true);



		Router router = new Router(getContext());
		router.attach("/sdis", model.SDISResource.class);
		
		return router;
	}

}
