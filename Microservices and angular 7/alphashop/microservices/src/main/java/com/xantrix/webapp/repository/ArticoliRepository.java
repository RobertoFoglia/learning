package com.xantrix.webapp.repository;

import com.xantrix.webapp.entity.Articolo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticoliRepository extends PagingAndSortingRepository<Articolo, String> {
    // @@@ REPOSITORY - NATIVE QUERY
    @Query(value = "SELECT * FROM ARTICOLI WHERE DESCRIZIONE LIKE :desArt", nativeQuery = true)
    List<Articolo> selByDescrizioneLike(@Param("desArt") String s);

    List<Articolo> findByDescrizioneLike(String s, Pageable pageable);

    Articolo findByCodArt(String s);
}
