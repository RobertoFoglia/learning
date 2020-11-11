package robertofoglia.pulsar.samples.services.topics.api;

import org.apache.pulsar.client.api.MessageId;
import robertofoglia.pulsar.samples.controllers.dtos.MessageDTO;

import java.util.concurrent.CompletableFuture;

public interface MyAvroTopicProducer {
    CompletableFuture<MessageId> send(MessageDTO message);
}
