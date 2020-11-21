package robertofoglia.pulsar.samples.controllers.exceptions.handlers;

import org.apache.pulsar.client.api.PulsarClientException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PulsarClientExceptionHandler implements ExceptionMapper<PulsarClientException> {
    @Override
    public Response toResponse(PulsarClientException e) {
        e.printStackTrace();
        return Response.serverError().entity(
                new ExceptionResponseEntity(e.getClass().getName(), e.getMessage())
        ).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
