package robertofoglia.pulsar.samples.controllers;

import org.apache.pulsar.client.api.MessageId;
import robertofoglia.pulsar.samples.controllers.dtos.MessageDTO;
import robertofoglia.pulsar.samples.services.topics.api.MyAvroTopicProducer;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletionStage;

@Path("/my-avro-topic/producers")
public class AvroProducersController {
    @Inject
    @Named("MyAvroTopicService")
    MyAvroTopicProducer producer;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @Path("/send")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<MessageId> sendMessage(MessageDTO message) {
        // if the pulsar is not available, then the AbstractChannel$AnnotatedConnectException is thrown
        return producer.send(message);
    }
}