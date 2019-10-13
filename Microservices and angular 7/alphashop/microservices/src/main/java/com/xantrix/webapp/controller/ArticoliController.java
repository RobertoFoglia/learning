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
            String errorMessage = "Il barcode " + barcode + " non è stato trovato!";
            LOGGER.warn(errorMessage);
            throw new NotFoundException(errorMessage);
        }

        return new ResponseEntity<Articolo>(barcodeObjcet.getArticolo(), HttpStatus.OK);
    }
}
