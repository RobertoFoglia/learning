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
                    Response.ok(helloWorldService.greeting())
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .build());
        });
    }
}
