package org.acme.services.hibernate;

import org.acme.entity.Anagram;
import org.acme.entity.AnagramSource;
import org.acme.repositories.hibernate.AnagramRepository;
import org.acme.repositories.hibernate.AnagramSourceRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.SQLException;

@ApplicationScoped
public class AnagramPanacheRepositoryService extends AnagramPanacheService {

    @Inject
    AnagramRepository anagramRepository;

    @Inject
    AnagramSourceRepository anagramSourceRepository;

    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = {SQLException.class, IOException.class})
    public String generateAndSaveAnagram(String sourceText) {
        String result = "generateAndSaveAnagram";
        AnagramSource anagramSource = new AnagramSource();
        anagramSource.setAnagramSourceText(sourceText);
        Anagram anagram = new Anagram();
        anagram.setAnagramText(result);
        anagram.setAnagramSource(anagramSource);
        anagramRepository.persist(anagram);
        return anagram.getAnagramText();
    }

}
