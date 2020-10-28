package org.acme;

import org.acme.services.HelloWorldService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class ExampleResource {

    @Inject
    @Named("default")
    private HelloWorldService helloWorldService;

    @Inject
    @Named("helloWorldWithRequestScope")
    private HelloWorldService helloWorldService2;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return helloWorldService.greeting();
    }

    @GET
    @Path("/2")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello2() {
        return helloWorldService2.greeting2();
    }
}