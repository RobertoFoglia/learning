package robertofoglia.pulsar.samples.services.topics;

import org.apache.pulsar.client.api.*;
import robertofoglia.pulsar.samples.controllers.dtos.MessageDTO;
import robertofoglia.pulsar.samples.services.topics.api.MyAvroTopicConsumer;
import robertofoglia.pulsar.samples.services.topics.api.MyAvroTopicProducer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class MyAvroTopicService implements MyAvroTopicProducer, MyAvroTopicConsumer {

    private Producer<MessageDTO> producer;
    private Consumer<MessageDTO> consumer;

    @ApplicationScoped
    @Produces
    @Named("MyAvroTopicService")
    MyAvroTopicService getMyAvroTopicService() throws PulsarClientException {
        String topicName = "my-avro-topic";
        PulsarClient client = PulsarClientFactory.getClient();

        producer = client.newProducer(Schema.AVRO(MessageDTO.class))
                .blockIfQueueFull(true)
                .topic(topicName)
                .create();

        consumer = client
                .newConsumer(Schema.AVRO(MessageDTO.class))
                .topic(topicName)
                .subscriptionName("my-subscription")
                .ackTimeout(10, TimeUnit.SECONDS)
                .subscribe();

        return this;
    }

    @Override
    public CompletableFuture<MessageId> send(MessageDTO message) {
        return producer.sendAsync(message).orTimeout(10, TimeUnit.SECONDS);
    }

    @Override
    public CompletableFuture<MessageDTO> receive() {
        CompletableFuture<MessageDTO> resultFuture = new CompletableFuture<>();
        consumer.receiveAsync().orTimeout(10, TimeUnit.SECONDS)
                .thenAcceptAsync(
                        message -> {
                            try {
                                // message elaboration (Pulsar wants the ack after elaboration)
                                System.out.println(message.getValue().getMessage());
                                consumer.acknowledge(message);
                            } catch (PulsarClientException e) {
                                resultFuture.completeExceptionally(e);
                            }
                            resultFuture.complete(message.getValue());
                        }
                );
        return resultFuture;
    }
}
