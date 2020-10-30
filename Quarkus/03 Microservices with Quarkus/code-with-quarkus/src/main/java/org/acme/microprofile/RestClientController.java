package org.acme.microprofile;

import lombok.extern.slf4j.Slf4j;
import org.acme.microprofile.external.services.GreetingDAO;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

@Path("/rest-client")
@Slf4j
public class RestClientController {
    @Inject
    @RestClient
    GreetingDAO greetingDAO;

    @Inject
    ManagedExecutor managedExecutor;

    @GET
    @Path("/hello")
    public String hello() {
        return greetingDAO.hello();
    }

    @GET
    @Path("/hello-from-rx")
    public String helloFromRx() throws ExecutionException, InterruptedException {
        // in the same thread of the request
        return greetingDAO.helloInRXWay().toCompletableFuture().get();
    }

    @GET
    @Path("/rx-hello")
    public CompletionStage<Response> rxHello() {
        // in another thread of the request
        final CompletionStage<Response>
                completionStageResponse = new CompletableFuture<>();
        managedExecutor.submit(() -> {
            log.info("RX - Executing in different thread");
            final Response response;

            try {
                response = Response.ok(greetingDAO.helloInRXWay().toCompletableFuture().get())
                        .type(MediaType.APPLICATION_JSON).
                                build();
                completionStageResponse.toCompletableFuture().complete(response);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        });
        log.info("end of the rest method - Executing in a different thread");
        return completionStageResponse;
    }

    @GET
    @Path("/inexistent-path")
    public String inexistentPath() {
        return greetingDAO.inexistentPath();
    }
}
