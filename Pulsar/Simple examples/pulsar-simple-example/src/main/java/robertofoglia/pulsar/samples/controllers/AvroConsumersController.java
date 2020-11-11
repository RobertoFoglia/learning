package robertofoglia.pulsar.samples.controllers;

import robertofoglia.pulsar.samples.controllers.dtos.MessageDTO;
import robertofoglia.pulsar.samples.services.topics.api.MyAvroTopicConsumer;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletionStage;

@Path("/my-avro-topic/consumers")
public class AvroConsumersController {
    @Inject
    @Named("MyAvroTopicService")
    MyAvroTopicConsumer consumer;

    @Path("/receive")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<MessageDTO> receive() {
        return consumer.receive();
    }
}
