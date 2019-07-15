package com.java.ee;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.java.ee.boundary.DocumentResouce;
import com.java.ee.boundary.VersionResource;

import org.glassfish.jersey.media.multipart.MultiPartFeature;

@ApplicationPath("api")
public class JAXWSConfiguration extends Application {

    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(DocumentResouce.class);
        classes.add(MultiPartFeature.class);
        classes.add(HelloWorldResource.class);
        classes.add(VersionResource.class);
        return classes;
    }
}
