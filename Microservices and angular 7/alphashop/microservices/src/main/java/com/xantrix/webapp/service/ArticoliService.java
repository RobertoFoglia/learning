package com.xantrix.webapp.service;

import com.xantrix.webapp.entity.Articolo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticoliService {
    Iterable<Articolo> selTutti();

    List<Articolo> selByDescrizione(String descrizione);

    List<Articolo> selByDescrizione(String descrizione, Pageable pageable);

    Articolo selByCodArt(String codArt);

    void delArticolo(Articolo articolo);

    void insArticolo(Articolo articolo);
}
