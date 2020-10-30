package org.acme;

import lombok.extern.slf4j.Slf4j;
import org.acme.services.HelloWorldService;
import org.eclipse.microprofile.context.ManagedExecutor;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Path("asyncAPIHello")
@Slf4j
public class AsynchronousRESTAPIController {
    @Inject
    @Named("default")
    HelloWorldService helloWorldService;

    @Inject
    ManagedExecutor managedExecutor;

    /* @@@ Async api - different thread pag 76 */
    @Path("/async")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void hello(@Suspended final AsyncResponse async) {
        managedExecutor.submit(() -> {
            log.info("Executing in a different thread");

            async.resume(
                    Response.ok(" Async -" + helloWorldService.greeting())
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .build());
        });
        log.info("end of the rest method - Executing in a different thread");
    }

    /* @@@ RX api - different thread pag 76 */
    @Path("/rx")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<Response> helloInRXWay() {
        final CompletionStage<Response>
                completionStageResponse = new CompletableFuture<>();
        managedExecutor.submit(() -> {
            log.info("RX - Executing in different thread");
            final Response response = Response.ok(" RX - " + helloWorldService.greeting())
                    .type(MediaType.APPLICATION_JSON).
                            build();
            completionStageResponse.toCompletableFuture().complete(response);
        });
        log.info("end of the rest method - Executing in a different thread");
        return completionStageResponse;
    }
}
