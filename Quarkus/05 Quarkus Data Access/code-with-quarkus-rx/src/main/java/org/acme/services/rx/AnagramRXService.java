package org.acme.services.rx;

import io.smallrye.mutiny.Multi;
import org.acme.entity.Anagram;
import org.acme.repositories.rx.AnagramRXRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AnagramRXService {
    @Inject
    AnagramRXRepository anagramRXRepository;

    public Multi<Anagram> findAllAnagramsBySourceId(long id) {
        return anagramRXRepository.findAllAnagramsBySourceId(id)
                .map(
                        anagram -> {
                            anagram.setAnagramText(anagram.getAnagramText() + "rob");
                            return anagram;
                        });
    }
}
