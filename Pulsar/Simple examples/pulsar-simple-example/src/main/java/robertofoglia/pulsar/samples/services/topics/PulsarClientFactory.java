package robertofoglia.pulsar.samples.services.topics;

import org.apache.pulsar.client.api.ClientBuilder;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

import static java.util.Objects.isNull;

public class PulsarClientFactory {
//    @Inject
//    @ConfigProperty(name = "pulsar.service.url", defaultValue = "")
//    public String url
// it doesn't work


    private static final String URL = "pulsar://localhost:6650";
    private static final ClientBuilder clientBuilder = PulsarClient.builder().serviceUrl(URL);

    private PulsarClientFactory() { }

    public static PulsarClient getClient() throws PulsarClientException {
        return clientBuilder.build();
    }
}
