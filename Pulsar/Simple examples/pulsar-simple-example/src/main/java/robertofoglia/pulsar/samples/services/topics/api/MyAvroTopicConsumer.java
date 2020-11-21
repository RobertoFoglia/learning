package robertofoglia.pulsar.samples.services.topics.api;

import robertofoglia.pulsar.samples.controllers.dtos.MessageDTO;

import java.util.concurrent.CompletableFuture;

public interface MyAvroTopicConsumer {
    CompletableFuture<MessageDTO> receive();
}
