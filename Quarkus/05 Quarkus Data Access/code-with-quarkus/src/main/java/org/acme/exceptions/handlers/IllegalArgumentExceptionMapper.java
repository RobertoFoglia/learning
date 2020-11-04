package org.acme.exceptions.handlers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/*
 * @@@ JAX-RS Exception handling
 */
@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {
    @Override
    public Response toResponse(IllegalArgumentException e) {
        return Response.serverError()
                .entity(e.getMessage())
                .status(Response.Status.BAD_REQUEST)
                .build();
    }
}
