package model;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class SDISResource extends ServerResource {

	@Get
	public String getRepresentation() {
		AgetacApp parentApp = (AgetacApp)getApplication();
		
		return parentApp.sdis.toString();
		
	}
	
	
}
