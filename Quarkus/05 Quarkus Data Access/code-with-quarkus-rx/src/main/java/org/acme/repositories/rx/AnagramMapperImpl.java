package org.acme.repositories.rx;

import io.vertx.mutiny.sqlclient.Row;
import org.acme.entity.Anagram;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AnagramMapperImpl implements AnagramMapper {

    @Override
    public Anagram fromRow(Row row) {
        Anagram newAnagram = new Anagram();
        newAnagram.setId(row.getLong("anagram_id"));
        newAnagram.setAnagramText(row.getString("anagram_text"));
        return newAnagram;

    }
}
