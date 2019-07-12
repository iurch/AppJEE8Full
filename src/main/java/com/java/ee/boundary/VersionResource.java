package com.java.ee.boundary;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("version")
@RequestScoped
public class VersionResource {
	
	@Path("/v1")
	public Class<Version1Resource> v1() {
		return Version1Resource.class;
	}
	
	@Path("/v2")
	public Class<Version2Resource> v2(){
		return Version2Resource.class;
	}
	
	
	
	@RequestScoped
	public static class Version1Resource {
		@Inject
		private Logger logger;
		
		
		@GET
		@Produces(MediaType.TEXT_PLAIN)
		public String get(){
			logger.log(Level.INFO, "Version 1.0 sub resource does not work.");
			return "v1.0";
		}
	}
	
	public static class Version2Resource{
		private final Logger logger = Logger.getLogger(Version2Resource.class.getName());
		
		@GET
		@Produces(MediaType.TEXT_PLAIN)
		public String get() {
			logger.log(Level.INFO, "Version 2.0 sub resource works.");
			return "v2.0";
		}
	}

}
