package org.acme.microprofile.external.services;

import org.acme.filters.LoggingFilter;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletionStage;

/* @@@ MicroProfile - REST CLIENT */
@RegisterRestClient(baseUri = "http://localhost:8080/")
@RegisterProvider(LoggingFilter.class)
public interface GreetingDAO {
    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    String hello();

    @GET
    @Path("asyncAPIHello/rx")
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<String> helloInRXWay();
}
