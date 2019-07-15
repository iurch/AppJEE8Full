
package com.java.ee.boundary;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 * DocumentResouce
 */
@RequestScoped
@Path("documents")
public class DocumentResouce {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @Context
    private ServletContext servletContext;

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("/me.jpg")
    @Produces("image/jpeg")
    public Response jpg(){
        String path = servletContext.getRealPath("/me.jpg");
        return Response.ok(new File(path))
                        .header("Content-Disposition", "attachment; file=me.jpg")
                        .build();
    }

    @GET
    @Path("/magic.gif")
    @Produces("magic/gif")
    public Response gif(){
        String path = servletContext.getRealPath("/magic.gif");
        return Response.ok(new File(path))
                        .build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@FormDataParam("file") InputStream inputStream,
                           @FormDataParam("file") FormDataContentDisposition fileInfo) {
        String fileName = fileInfo.getFileName();
        saveFile(inputStream, fileName);

        URI uri = uriInfo.getBaseUriBuilder().path(DocumentResouce.class).path(fileName).build();
        return Response.created(uri).build();
    }


    private void saveFile(InputStream file, String name){
        try{
            java.nio.file.Path path = java.nio.file.FileSystems.getDefault().getPath("/tmp/", name);
            Files.copy(file,path);
        } catch(IOException e){
            LOGGER.log(Level.WARNING, "Unable to save file.", e);
        }
    }
    
}