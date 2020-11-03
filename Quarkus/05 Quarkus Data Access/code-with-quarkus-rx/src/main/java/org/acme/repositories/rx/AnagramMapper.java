package org.acme.repositories.rx;

import io.vertx.mutiny.sqlclient.Row;
import org.acme.entity.Anagram;

public interface AnagramMapper {
     Anagram fromRow(Row row);
}
