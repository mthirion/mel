package org.jboss.integration.examples;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//@Path("/service")
// BUG Hawtio > API WADL for live test 
public class Service {

	@GET
	@Path("/flow")
	public String getAgent(String msg) throws Exception {

		System.out.println("Rest GET message arrived: " + msg);
		return null;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_XML)
	@Path("/flow")
	public String addAgent(String msg) throws Exception {

		System.out.println("Rest POST message arrived: " + msg);
		return null;
	}
}