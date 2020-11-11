package robertofoglia.pulsar.samples.services.topics.api;

import org.apache.pulsar.client.api.MessageId;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public interface MyTopicProducer {
    <R> CompletableFuture<R> send(String message, Function<MessageId, R> onComplete);
}
