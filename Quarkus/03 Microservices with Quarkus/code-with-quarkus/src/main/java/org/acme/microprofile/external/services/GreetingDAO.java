package org.acme.microprofile.external.services;

import org.acme.exceptions.handlers.GreetingDAOExceptionHandler;
import org.acme.filters.LoggingFilter;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletionStage;

/* @@@ MicroProfile - REST CLIENT */
@RegisterRestClient(baseUri = "http://localhost:8080/")
@RegisterProvider(LoggingFilter.class)
@RegisterProvider(GreetingDAOExceptionHandler.class)
public interface GreetingDAO {
    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    String hello();

    @GET
    @Path("asyncAPIHello/rx")
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<String> helloInRXWay();

    @GET
    @Path("/inexistentPath")
    /* @@@ MicroProfile - Fault Tolerance */
    @Retry(
            delay = 2000,
            delayUnit = ChronoUnit.MILLIS,
            maxRetries = 4
            /*, retryOn = {SocketTimeoutException.class, ConnectException.
            class*/
    )
    String inexistentPath();
}
