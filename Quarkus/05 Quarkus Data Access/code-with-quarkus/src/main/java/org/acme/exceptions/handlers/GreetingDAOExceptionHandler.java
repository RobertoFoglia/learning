package org.acme.exceptions.handlers;

import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.ws.rs.core.Response;
import java.util.concurrent.ExecutionException;

public class GreetingDAOExceptionHandler implements ResponseExceptionMapper<ExecutionException> {
    @Override
    public ExecutionException toThrowable(Response response) {
        return new ExecutionException(
                new RuntimeException(response.readEntity(String.class))
        );
    }
}
