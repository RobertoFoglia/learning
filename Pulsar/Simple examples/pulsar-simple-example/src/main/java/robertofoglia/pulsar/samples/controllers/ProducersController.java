package robertofoglia.pulsar.samples.controllers;

import robertofoglia.pulsar.samples.services.topics.api.MyTopicProducer;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletionStage;

@Path("/my-topic/producers")
public class ProducersController {
    @Inject
    @Named("MyTopicService")
    MyTopicProducer producer;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @Path("/send")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<String> sendMessage(String message) {
        // if the pulsar is not available, then the AbstractChannel$AnnotatedConnectException is thrown
        return producer.send(message,
                messageId -> {
                    System.out.println(messageId.toString());
                    return messageId.toString();
                });
    }
}