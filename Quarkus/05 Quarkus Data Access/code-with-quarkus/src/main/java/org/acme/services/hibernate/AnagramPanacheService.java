package org.acme.services.hibernate;

import org.acme.entity.Anagram;
import org.acme.entity.AnagramSource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.*;

@ApplicationScoped
public class AnagramPanacheService {

    @Inject
    UserTransaction userTransaction;

    public String generateAndSaveAnagram(String sourceText) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        String result = "Scrambler.scramble(sourceText)";
        AnagramSource anagramSource = new AnagramSource();
        anagramSource.setAnagramSourceText(sourceText);
        Anagram anagram = new Anagram();
        anagram.setAnagramText(result);
        userTransaction.begin();
        anagramSource.persist();
        anagram.setAnagramSource(anagramSource);
        anagram.persist();
        userTransaction.commit();
        return anagram.getAnagramText();
    }

}
