package com.xantrix.webapp.controller;

import com.xantrix.webapp.entity.Articolo;
import com.xantrix.webapp.entity.Barcode;
import com.xantrix.webapp.exception.NotFoundException;
import com.xantrix.webapp.service.ArticoliService;
import com.xantrix.webapp.service.BarcodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/articoli")
public class ArticoliController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticoliController.class);

    @Autowired
    private ArticoliService articoliService;

    @Autowired
    private BarcodeService barcodeService;

    @GetMapping(value = "/cerca/ean/{barcode}", produces = "application/json")
    public ResponseEntity<Articolo> listArtByEan(@PathVariable("barcode") String barcode)
            throws NotFoundException {
        LOGGER.info("****** Otteniamo l'articolo con barcode " + barcode + " *******");

        Barcode barcodeObjcet = barcodeService.selByBarcode(barcode);
        if (barcodeObjcet == null) {
            return throwNotFoundException("Il barcode " + barcode + " non è stato trovato!");
        }

        return new ResponseEntity<Articolo>(barcodeObjcet.getArticolo(), HttpStatus.OK);
    }

    @GetMapping(value = "/cerca/codice/{codart}", produces = "application/json")
    public ResponseEntity<Articolo> listArtByCodArt(@PathVariable("codart") String CodArt)
            throws NotFoundException {
        LOGGER.info("****** Otteniamo l'articolo con codice " + CodArt + " *******");

        Articolo articolo = articoliService.selByCodArt(CodArt);

        if (articolo == null)
            throwNotFoundException("L'articolo con codice " + CodArt +" non è stato trovato!");

        return new ResponseEntity<Articolo>(articolo, HttpStatus.OK);
    }

    @GetMapping(value = "/cerca/descrizione/{filter}", produces = "application/json")
    public ResponseEntity<List<Articolo>> listArtByDesc(@PathVariable("filter") String Filter)
            throws NotFoundException {
        LOGGER.info("****** Otteniamo gli articoli con Descrizione: " + Filter + " *******");

        List<Articolo> articoli = articoliService.selByDescrizione(Filter.toUpperCase() + "%");

        if (articoli == null)
            throwNotFoundException("Non è stato trovato alcun articolo avente descrizione " + Filter);

        return new ResponseEntity<List<Articolo>>(articoli, HttpStatus.OK);
    }

    private ResponseEntity<Articolo> throwNotFoundException(String errorMessage) throws NotFoundException {
        LOGGER.warn(errorMessage);
        throw new NotFoundException(errorMessage);
    }
}
