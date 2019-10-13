package com.xantrix.webapp.service;

import com.xantrix.webapp.entity.Articolo;
import com.xantrix.webapp.repository.ArticoliRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ArticoliServiceImpl implements ArticoliService {
    private final ArticoliRepository articoliRepository;

    public ArticoliServiceImpl(ArticoliRepository articoliRepository) {
        this.articoliRepository = articoliRepository;
    }

    @Override
    public Iterable<Articolo> selTutti() {
        return articoliRepository.findAll();
    }

    @Override
    public List<Articolo> selByDescrizione(String descrizione) {
        return articoliRepository.selByDescrizioneLike(descrizione);
    }

    @Override
    public List<Articolo> selByDescrizione(String descrizione, Pageable pageable) {
        return articoliRepository.findByDescrizioneLike(descrizione, pageable);
    }

    @Override
    public Articolo selByCodArt(String codArt) {
        return articoliRepository.findByCodArt(codArt);
    }

    @Override
    @Transactional
    public void delArticolo(Articolo articolo) {
        articoliRepository.delete(articolo);
    }

    @Override
    @Transactional
    public void insArticolo(Articolo articolo) {
        articoliRepository.save(articolo);
    }

}
