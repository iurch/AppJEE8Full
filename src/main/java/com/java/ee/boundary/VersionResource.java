package com.java.ee.boundary;

import java.util.Collections;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("version")
@RequestScoped
public class VersionResource {

	public static final MediaType v1 = new MediaType("application", "vnd.version.v1+json");
	public static final MediaType v2 = new MediaType("application", "vnd.version.v2+json");

	@GET
	@Produces("application/vnd.version.v2+json")
	public Response v2(){
		Map<String,String> map = Collections.singletonMap("version", "v2");
		return Response.ok(map).build();
	}

	@GET
	@Produces({"application/json; qs=0.75","application/vnd.version.v1+json; qs=1.0"})
	public Response v1(){
		Map<String,String> map = Collections.singletonMap("version","v1");
		return Response.ok(map).build();
	}
	
	// @Path("/v1")
	// public Class<Version1Resource> v1() {
	// 	return Version1Resource.class;
	// }
	
	// @Path("/v2")
	// public Class<Version2Resource> v2(){
	// 	return Version2Resource.class;
	// }
	
	
	
	// @RequestScoped
	// public static class Version1Resource {
	// 	@Inject
	// 	private Logger logger;
		
		
	// 	@GET
	// 	@Produces(MediaType.TEXT_PLAIN)
	// 	public String get(){
	// 		logger.log(Level.INFO, "Version 1.0 sub resource does not work.");
	// 		return "v1.0";
	// 	}
	// }
	
	// public static class Version2Resource{
	// 	private final Logger logger = Logger.getLogger(Version2Resource.class.getName());
		
	// 	@GET
	// 	@Produces(MediaType.TEXT_PLAIN)
	// 	public String get() {
	// 		logger.log(Level.INFO, "Version 2.0 sub resource works.");
	// 		return "v2.0";
	// 	}
	// }

}
