package com.xantrix.webapp.service;

import com.xantrix.webapp.entity.Barcode;
import com.xantrix.webapp.repository.BarcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Lazy
@Transactional(readOnly = true)
public class BarcodeServiceImpl implements BarcodeService {

    @Autowired
    @Lazy
    private BarcodeRepository barcodeRepository;

    @Override
    public Barcode selByBarcode(String barcode) {
        return barcodeRepository.findByBarcode(barcode);
    }
}
