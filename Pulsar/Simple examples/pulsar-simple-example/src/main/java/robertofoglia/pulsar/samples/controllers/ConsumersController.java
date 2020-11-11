package robertofoglia.pulsar.samples.controllers;

import robertofoglia.pulsar.samples.services.topics.api.MyTopicConsumer;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletionStage;

@Path("/consumers")
public class ConsumersController {
    @Inject
    @Named("MyTopicService")
    MyTopicConsumer consumer;

    @Path("/receive")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public CompletionStage<String> receive() {
        return consumer.receive();
    }
}
