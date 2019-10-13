package com.xantrix.webapp.service;

import com.xantrix.webapp.repository.ArticoliRepository;
import com.xantrix.webapp.repository.BarcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class ServiceConfigurations {

    @Autowired
    private ArticoliRepository articoliRepository;

    @Autowired
    private BarcodeRepository barcodeRepository;

    @Bean
    @Lazy
    public ArticoliService articoliService() {
        return new ArticoliServiceImpl(articoliRepository);
    }

    @Bean
    @Lazy
    public BarcodeService barcodeService() {
        return new BarcodeServiceImpl(barcodeRepository);
    }
}
