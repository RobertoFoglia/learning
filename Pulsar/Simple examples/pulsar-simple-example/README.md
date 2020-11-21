# pulsar-simple-example project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Pulsar
Here we use a Java Client and we make an example with the instruction on the [Pulsar docs](https://pulsar.apache.org/docs/en/client-libraries-java/)

To start it
$ docker run -it \
  -p 6650:6650 \
  -p 8080:8080 \
  --mount source=pulsardata,target=/pulsar/data \
  --mount source=pulsarconf,target=/pulsar/conf \
  apachepulsar/pulsar:2.6.1 \
  bin/pulsar standalone

### Main feature
Pulsar provides guaranteed message delivery for applications. If a message successfully reaches a Pulsar broker, it will be delivered to its intended target (ack and unack message). 

The delivery is based on the ack/unack state of the messages and for default the all acknowledged messages are deleted. 

Consumer gets the message, it processes it then sents the ack 

Pulsar topics enable you to persistently store as many unacknowledged messages as you need while preserving message ordering. By default, Pulsar stores all unacknowledged/unprocessed messages produced on a topic. 

Accumulating many unacknowledged messages on a topic is necessary for many Pulsar use cases but it can also be very time intensive for Pulsar consumers to "rewind" through the entire log of messages. 

Topic compaction - Pulsar goes through a topic's backlog and removes messages that are obscured by later messages (automatic) 

Data written to BookKeeper is replicated to 3 physical machine replicas 

Pulsar currently supports S3, Google Cloud Storage (GCS), and filesystem for long term store. 

Pulsar provides a built-in service discovery mechanism that you can set up using the instructions in the Deploying a Pulsar instance guide. 

The persistent / not-persistent topic 

Message deduplication 

Topic partitioning  

Schema registry

### Client library feature 
The current official Pulsar client libraries support transparent reconnection and/or connection failover to brokers, queuing of messages until acknowledged by the broker, and heuristics such as connection retries with backoff. 

Whenever the TCP connection breaks, the client will immediately re-initiate this setup phase and will keep trying with exponential backoff to re-establish the producer or consumer until the operation succeeds. 

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Swagger
Here you can try the REST API.

http://localhost:8081/docs/api/

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `pulsar-simple-example-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/pulsar-simple-example-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/pulsar-simple-example-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.