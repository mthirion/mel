package org.jboss.integration.examples;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/service")
public class Service {

	@GET
	//@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	@Path("/flow")
	public String processAuthService(String msg) throws Exception {

		System.out.println("Rest GET message arrived: " + msg);
		return null;
	}

}