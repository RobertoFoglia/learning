package org.acme.interceptors;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* @@@ JAX-RS interceptors - business general manipulation - pag 71*/
@Provider
@Slf4j
public class ReaderAndWriterInterceptor implements ReaderInterceptor, WriterInterceptor {
    /* outbound */
    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        final OutputStream outputStream = context.getOutputStream();
        outputStream.write(" appending of the ReaderAndWriterInterceptor ---- ".getBytes());
        context.setOutputStream(outputStream);

        context.proceed();
    }

    /* inbound */
    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
        final InputStream inputStream = context.getInputStream();
        String body = new String(inputStream.readAllBytes());
        if (! "".equals(body)) {
            log.info("The message is not blank.");
            body = body + " >_>_>_>_>_>_ ";
            context.setInputStream(new ByteArrayInputStream(body.getBytes()));
        } else {
            // it doesn't work
            context.setInputStream(new ByteArrayInputStream("ByteArrayInputStream".getBytes()));
        }

        return context.proceed();
    }
}
