package com.xantrix.webapp.repository;

import com.xantrix.webapp.entity.Articoli;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticoliRepository extends PagingAndSortingRepository<Articoli, String> {
    // @@@ REPOSITORY - NATIVE QUERY
    @Query(value = "SELECT * FROM ARTICOLI WHERE DESCRIZIONE LIKE :desArt", nativeQuery = true)
    List<Articoli> SelByDescrizioneLike(@Param("desArt") String s);

    List<Articoli> findByDescrizioneLike(String s, Pageable pageable);

    Articoli findByCodArt(String s);
}
