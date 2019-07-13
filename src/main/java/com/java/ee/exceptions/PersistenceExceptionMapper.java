package com.java.ee.exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * PersistenceExceptionMapper
 */
@Provider
public class PersistenceExceptionMapper implements ExceptionMapper<PersistenceException> {

    @Override
    public Response toResponse(PersistenceException exception) {
        if(exception instanceof EntityNotFoundException){
            return Response.status(Response.Status.NOT_FOUND).build();
        } 

        Map<String, String> response = new HashMap();
        response.put("code", "ERR-GENERAL");
        response.put("type", "DATABASE");
        response.put("message", exception.getMessage());
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(response)
                .type(MediaType.APPLICATION_JSON)
                .build();
        
    }

    
}