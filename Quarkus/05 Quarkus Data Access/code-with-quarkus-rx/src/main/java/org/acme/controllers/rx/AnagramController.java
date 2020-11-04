package org.acme.controllers.rx;

import io.smallrye.mutiny.Multi;
import org.acme.entity.Anagram;
import org.acme.services.rx.AnagramRXService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/anagram")
public class AnagramController {

    @Inject
    AnagramRXService anagramRXService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Anagram> findAllAnagramsBySourceId(@PathParam("id") long id) {
        return anagramRXService.findAllAnagramsBySourceId(id);
    }
}
