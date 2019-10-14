package com.xantrix.webapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xantrix.webapp.entity.Articolo;
import com.xantrix.webapp.entity.Barcode;
import com.xantrix.webapp.exception.BindingException;
import com.xantrix.webapp.exception.DuplicateException;
import com.xantrix.webapp.exception.NotFoundException;
import com.xantrix.webapp.service.ArticoliService;
import com.xantrix.webapp.service.BarcodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/articoli")
public class ArticoliController {
    private static final Logger logger = LoggerFactory.getLogger(ArticoliController.class);

    @Autowired
    private ArticoliService articoliService;

    @Autowired
    private BarcodeService barcodeService;

    @Autowired
    private ResourceBundleMessageSource errMessage;

    @GetMapping(value = "/cerca/ean/{barcode}", produces = "application/json")
    public ResponseEntity<Articolo> listArtByEan(@PathVariable("barcode") String barcode)
            throws NotFoundException {
        logger.info("****** Otteniamo l'articolo con barcode " + barcode + " *******");

        Barcode barcodeObjcet = barcodeService.selByBarcode(barcode);
        if (barcodeObjcet == null) {
            return throwNotFoundException("Il barcode " + barcode + " non è stato trovato!");
        }

        return new ResponseEntity<Articolo>(barcodeObjcet.getArticolo(), HttpStatus.OK);
    }

    @GetMapping(value = "/cerca/codice/{codart}", produces = "application/json")
    public ResponseEntity<Articolo> listArtByCodArt(@PathVariable("codart") String CodArt)
            throws NotFoundException {
        logger.info("****** Otteniamo l'articolo con codice " + CodArt + " *******");

        Articolo articolo = articoliService.selByCodArt(CodArt);

        if (articolo == null)
            throwNotFoundException("L'articolo con codice " + CodArt +" non è stato trovato!");

        return new ResponseEntity<Articolo>(articolo, HttpStatus.OK);
    }

    @GetMapping(value = "/cerca/descrizione/{filter}", produces = "application/json")
    public ResponseEntity<List<Articolo>> listArtByDesc(@PathVariable("filter") String Filter)
            throws NotFoundException {
        logger.info("****** Otteniamo gli articoli con Descrizione: " + Filter + " *******");

        List<Articolo> articoli = articoliService.selByDescrizione(Filter.toUpperCase() + "%");

        if (articoli == null)
            throwNotFoundException("Non è stato trovato alcun articolo avente descrizione " + Filter);

        return new ResponseEntity<List<Articolo>>(articoli, HttpStatus.OK);
    }

    // ------------------- INSERIMENTO ARTICOLO ------------------------------------
    @PostMapping(value = "/inserisci")
    public ResponseEntity<Articolo> createArt(@Valid @RequestBody Articolo articolo, BindingResult bindingResult)
            throws BindingException, DuplicateException {
        logger.info("Salviamo l'articolo con codice " + articolo.getCodArt());

        //controllo validità dati articolo
        if (bindingResult.hasErrors()) {
            String msgErr = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale());

            logger.warn(msgErr);

            throw new BindingException(msgErr);
        }

        //Disabilitare se si vuole gestire anche la modifica
        Articolo checkArt = articoliService.selByCodArt(articolo.getCodArt());

        if (checkArt != null) {
            String msgErr = String.format("Articolo %s presente in anagrafica! "
                    + "Impossibile utilizzare il metodo POST", articolo.getCodArt());

            logger.warn(msgErr);

            throw new DuplicateException(msgErr);
        }

        articoliService.insArticolo(articolo);

        return new ResponseEntity<Articolo>(new HttpHeaders(), HttpStatus.CREATED);
    }

    // ------------------- MODIFICA ARTICOLO ------------------------------------
    @RequestMapping(value = "/modifica", method = RequestMethod.PUT)
    public ResponseEntity<Articolo> updateArt(@Valid @RequestBody Articolo articolo, BindingResult bindingResult)
            throws BindingException, NotFoundException {
        logger.info("Modifichiamo l'articolo con codice " + articolo.getCodArt());

        if (bindingResult.hasErrors()) {
            String MsgErr = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale());
            logger.warn(MsgErr);
            throw new BindingException(MsgErr);
        }

        Articolo checkArt = articoliService.selByCodArt(articolo.getCodArt());

        if (checkArt == null)
            throwNotFoundException("Articolo" + articolo.getCodArt() + "non presente in anagrafica! Impossibile utilizzare il metodo PUT");

        articoliService.insArticolo(articolo);

        return new ResponseEntity<Articolo>(new HttpHeaders(), HttpStatus.CREATED);
    }

    // ------------------- ELIMINAZIONE ARTICOLO ------------------------------------
    @RequestMapping(value = "/elimina/{codart}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> deleteArt(@PathVariable("codart") String codArt)
            throws NotFoundException {
        logger.info("Eliminiamo l'articolo con codice " + codArt);

        Articolo articolo = articoliService.selByCodArt(codArt);

        if (articolo == null)
            throwNotFoundException("Articolo " + codArt + "non presente in anagrafica!");

        articoliService.delArticolo(articolo);

        /// @@@ custom response
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eliminazione Articolo " + codArt + " Eseguita Con Successo");

        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK);

    }

    private ResponseEntity<Articolo> throwNotFoundException(String errorMessage) throws NotFoundException {
        logger.warn(errorMessage);
        throw new NotFoundException(errorMessage);
    }
}
