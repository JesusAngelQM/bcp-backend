package com.jaqm.bcp.controller;

import com.jaqm.bcp.dto.ExchangeRateDto;
import com.jaqm.bcp.model.Currency;
import com.jaqm.bcp.service.CurrencyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jesus QM
 */
@RestController
@RequestMapping("currency")
public class CurrencyController {
    
    @Autowired
    private CurrencyService currencyService;
    
    protected final Logger logger = LogManager.getLogger(getClass());
    
    @GetMapping("")
    public Iterable<Currency> index() {
        logger.info("Mostrar lista de monedas");
        return currencyService.getCurrencies();
    }
    
    @PostMapping(value = "/store", consumes = "application/json")
    public Currency store(@RequestBody Currency data) {
        logger.info("Registrar moneda");
        return currencyService.store(data);
    }
    
    @PostMapping(value = "/update", consumes = "application/json")
    public Currency update(@RequestBody Currency data) {
        logger.info("Actualizar lista de monedas");
        return currencyService.update(data);
    }
    
    @GetMapping("/searchExchangeRate")
    public ExchangeRateDto searchExchangeRate(
            @RequestParam(value = "monto",required = true) Double monto,
            @RequestParam(value = "moneda_origen",required = true) String moneda_origen,
            @RequestParam(value = "moneda_destino",required = true) String moneda_destino
    ) {
        logger.info("Consultar por tipo de cambio");
        return currencyService.searchExchangeRate(monto,moneda_origen,moneda_destino);
    }
    
}
