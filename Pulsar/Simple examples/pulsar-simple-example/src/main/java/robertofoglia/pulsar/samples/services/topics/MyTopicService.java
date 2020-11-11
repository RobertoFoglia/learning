package robertofoglia.pulsar.samples.services.topics;

import org.apache.pulsar.client.api.*;
import robertofoglia.pulsar.samples.services.topics.api.MyTopicConsumer;
import robertofoglia.pulsar.samples.services.topics.api.MyTopicProducer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class MyTopicService implements MyTopicProducer, MyTopicConsumer {

    private Producer<byte[]> producer;
    private Consumer<byte[]> consumer;

    private static int count = 0;
    private ReaderBuilder<byte[]> readerBuilder;
    private Reader<byte[]> reader;
    private boolean isReaderSetOnTheFirstMessageSinceStartup = false;

    @ApplicationScoped
    @Produces
    @Named("MyTopicService")
    MyTopicService getMyTopicService() throws PulsarClientException {
        String topicName = "my-topic";
        PulsarClient client = PulsarClientFactory.getClient();
        producer = client
                .newProducer()
                .blockIfQueueFull(true)
                .topic(topicName).create();

        consumer = client
                .newConsumer()
                .topic(topicName)
                .subscriptionName("my-subscription")
                .ackTimeout(10, TimeUnit.SECONDS)
                .subscribe();

        readerBuilder = client.newReader().topic(topicName).startMessageId(MessageId.earliest);
        reader = readerBuilder.create();
        return this;
    }

    @Override
    public <R> CompletableFuture<R> send(String message, Function<MessageId, R> onComplete) {
        String s = message + " " + ++count;
        return producer.sendAsync(s.getBytes())
                .thenApply(messageId -> {
                    System.out.println(s);
                    if (!isReaderSetOnTheFirstMessageSinceStartup) {
                        readerBuilder.startMessageId(messageId).createAsync()
                                .thenAccept(reader1 -> reader = reader1)
                                .exceptionally(throwable -> {
                                    throwable.printStackTrace();
                                    return Void.TYPE.cast(throwable);
                                });
                        isReaderSetOnTheFirstMessageSinceStartup = true;
                    }
                    return messageId;
                }).thenApply(onComplete);
    }

    @Override
    public CompletableFuture<String> receive() {
        CompletableFuture<String> resultFuture = new CompletableFuture<>();
        consumer.receiveAsync().orTimeout(10, TimeUnit.SECONDS)
                .thenAcceptAsync(
                        message -> {
                            try {
                                // message elaboration (Pulsar wants the ack after elaboration)
                                consumer.acknowledge(message);
                            } catch (PulsarClientException e) {
                                resultFuture.completeExceptionally(e);
                            }
                            resultFuture.complete(new String(message.getData()));
                        }
                );
        return resultFuture;
    }

    @Override
    public CompletableFuture<String> read() {
        return reader.readNextAsync().orTimeout(10, TimeUnit.SECONDS).thenApply(message -> new String(message.getData()));
    }
}
