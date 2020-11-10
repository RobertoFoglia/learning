package robertofoglia.pulsar.samples.services.topics;

import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClientException;
import robertofoglia.pulsar.samples.services.topics.api.MyTopicProducer;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@ApplicationScoped
public class MyTopicService implements MyTopicProducer {

    private final Producer<byte[]> producer;

    public MyTopicService() throws PulsarClientException {
        producer = PulsarClientFactory.getClient()
                .newProducer()
                .blockIfQueueFull(true)
                .topic("my-topic").create();
    }

    @Override
    public <R> CompletableFuture<R> send(String message, Function<MessageId, R> onComplete) {
        return producer.sendAsync(message.getBytes()).thenApply(onComplete);
    }

    @Override
    public void send(List<String> messages) {
        return;
    }
}
