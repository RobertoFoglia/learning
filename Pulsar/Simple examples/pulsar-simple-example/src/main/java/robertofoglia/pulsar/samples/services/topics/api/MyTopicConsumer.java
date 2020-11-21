package robertofoglia.pulsar.samples.services.topics.api;

import java.util.concurrent.CompletableFuture;

public interface MyTopicConsumer {
    CompletableFuture<String> receive();

    CompletableFuture<String> read();
}
