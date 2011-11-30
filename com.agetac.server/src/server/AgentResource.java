package server;

import model.Agent;

import org.restlet.Request;
import org.restlet.data.Form;
import org.restlet.data.MediaType;

import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.restlet.resource.ResourceException;
import org.restlet.representation.StringRepresentation;
import org.restlet.representation.Variant;

public class AgentResource extends ServerResource {

	private Agent agent = null;
	
	public AgentResource() {
		String agentid = null;
		agentid = (String) Request.getCurrent().getAttributes().get("aid");
		this.agent = findAgent(agentid);
		System.out.println(Request.getCurrent().getAttributes().toString());
		//getVariants().add(new Variant(MediaType.TEXT_PLAIN));
		//getVariants().add(new Variant(MediaType.APPLICATION_JSON));
		
	}


	/**
	 * Find the requested agent object
	 * 
	 * @param agentid
	 * @return
	 */
	private Agent findAgent(String agentid) {
		try {
			System.out.println("find Agent "+ agentid);
			
			if (null == agentid)
				return null;
			// :TODO {do some database lookup here }
			// agent = result of lookup
			// This part should be replaced by a lookup
			Agent a = new Agent();
			a.setId(Integer.parseInt(agentid));
			a.setNom("Foo");
			a.setSuperieur(new Agent("Bob", null, null, null));
			// end replace
			return a;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Represent the agent object in the requested format.
	 * 
	 * @param variant
	 * @return
	 * @throws ResourceException
	 */
	@Get
	public Representation getAgent(Variant variant) throws ResourceException {
		System.out.println("get");
		Representation result = null;
		if (null == this.agent) {
			ErrorMessage em = new ErrorMessage();
			return representError(variant, em);
		} else {
			if (variant.getMediaType().equals(MediaType.APPLICATION_JSON)) {
				result = new JsonRepresentation(this.agent.toJSON());
			} else {
				result = new StringRepresentation(this.agent.toString());
				//result = new JsonRepresentation(this.agent.toJSON());
			}
		}
		return result;
	}
	/**
	 * Handle a POST Http request. Create a new agent
	 * 
	 * @param entity
	 * @throws ResourceException
	 */
	@Post
	public void createAgent(Representation entity)
			throws ResourceException {
		// We handle only a form request in this example. Other types could be
		// JSON or XML.
		System.out.println("post");
		try {
			if (entity.getMediaType().equals(MediaType.APPLICATION_WWW_FORM,
					true)) {
				Form form = new Form(entity);
				Agent u = new Agent();
				u.setNom(form.getFirstValue("user[name]"));
				// :TODO {save the new user to the database}
				getResponse().setStatus(Status.SUCCESS_OK);
				// We are setting the representation in the example always to
				// JSON.
				// You could support multiple representation by using a
				// parameter
				// in the request like "?response_format=xml"
				Representation rep = new JsonRepresentation(u.toJSON());
				getResponse().setEntity(rep);
			} else {
				getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			}
		} catch (Exception e) {
			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);
		}
	}

	/**
	 * Handle a PUT Http request. Update an existing agent
	 * 
	 * @param entity
	 * @throws ResourceException
	 */
	@Put
	public void updateAgent(Representation entity)
			throws ResourceException {
		System.out.println("put");
		try {
			if (null == this.agent) {
				ErrorMessage em = new ErrorMessage();
				Representation rep = representError(entity.getMediaType(), em);
				getResponse().setEntity(rep);
				getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
				return;
			}
			if (entity.getMediaType().equals(MediaType.APPLICATION_WWW_FORM,
					true)) {
				Form form = new Form(entity);
				this.agent.setNom(form.getFirstValue("user[name]"));
				// :TODO {update the new user in the database}
				getResponse().setStatus(Status.SUCCESS_OK);
				// We are setting the representation in this example always to
				// JSON.
				// You could support multiple representation by using a
				// parameter
				// in the request like "?response_format=xml"
				Representation rep = new JsonRepresentation(this.agent.toJSON());
				getResponse().setEntity(rep);
			} else {
				getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			}
		} catch (Exception e) {
			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);
		}
	}

	/**
	 * Handle a DELETE Http Request. Delete an existing agent
	 * 
	 * @param entity
	 * @throws ResourceException
	 */
	@Delete
	public void removeAgent() throws ResourceException {
		try {
			System.out.println("delete");
			if (null == this.agent) {
				ErrorMessage em = new ErrorMessage();
				Representation rep = representError(MediaType.APPLICATION_JSON,
						em);
				getResponse().setEntity(rep);
				getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
				return;
			}
			// :TODO {delete the user from the database}
			getResponse().setStatus(Status.SUCCESS_OK);
		} catch (Exception e) {
			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);
		}
	}

	/**
	 * Represent an error message in the requested format.
	 * 
	 * @param variant
	 * @param em
	 * @return
	 * @throws ResourceException
	 */
	private Representation representError(Variant variant, ErrorMessage em)
			throws ResourceException {
		Representation result = null;
		if (variant.getMediaType().equals(MediaType.APPLICATION_JSON)) {
			result = new JsonRepresentation(em.toJSON());
		} else {
			result = new StringRepresentation(em.toString());
		}
		return result;
	}

	protected Representation representError(MediaType type, ErrorMessage em)
			throws ResourceException {
		Representation result = null;
		if (type.equals(MediaType.APPLICATION_JSON)) {
			result = new JsonRepresentation(em.toJSON());
		} else {
			result = new StringRepresentation(em.toString());
		}
		return result;
	}
}