package org.acme.repositories.rx;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Tuple;
import org.acme.entity.Anagram;
import org.hibernate.reactive.mutiny.Mutiny;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.stream.StreamSupport;

@ApplicationScoped
public class AnagramRXRepository {
    @Inject
    PgPool reactivePgPool;

    @Inject
    AnagramMapper anagramMapper;

    @Inject
    Uni<Mutiny.Session> mutinySession;

    /* without hibernate */
    public Multi<Anagram> findAllAnagramsBySourceId(Long id) {
        return reactivePgPool.preparedQuery("select * from anagram where anagram_source_id = $1").execute(Tuple.of(id))
                .onItem().transformToMulti(rows -> Multi.createFrom().items(() -> StreamSupport.stream(rows.spliterator(), false)))
                .map(row -> anagramMapper.fromRow(row));
    }

    /* with hibernate */
//    public Multi<Anagram> findAllAnagramsBySourceId(long id) {
//        Anagram anagram1 = new Anagram();
//        AnagramSource anagramSource = new AnagramSource();
//        anagramSource.setId(1L);
//        anagram1.setAnagramSource(anagramSource);
//        mutinySession.flatMap(session -> session.fetch(anagram1)).onItem()
//                .transformToMulti(anagram -> )
//                .map(row -> anagramMapper.fromRow(row))
//                // TODO to continue https://github.com/hibernate/hibernate-reactive/blob/master/example/src/main/java/org/hibernate/example/reactive/MutinyMain.java
//    }

}
