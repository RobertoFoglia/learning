package org.acme.filters;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/* @@@ Filters - they give me fine-grained access to the payloads - pag 68 */
@Provider
@Priority(1)
@Slf4j
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {
    /**
     * Inbound
     * @param requestContext
     * @throws IOException
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        BufferedInputStream iStream = new BufferedInputStream(requestContext.getEntityStream());
        byte[] inputContent = new byte[iStream.available()];
        iStream.read(inputContent);
        String body = new String(inputContent, "utf-8");

        log.info("Inside request filter. Message size:" + inputContent.length + "; Message on the way in: " + body);
        requestContext.setEntityStream(new ByteArrayInputStream(inputContent));
    }

    /**
     * Outbound
     * @param requestContext
     * @param responseContext
     * @throws IOException
     */
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        log.info("Message on the way out " + responseContext.getEntity());
    }
}
