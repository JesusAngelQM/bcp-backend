package com.jaqm.bcp.controller;

import com.jaqm.bcp.dto.ExchangeRateDto;
import com.jaqm.bcp.model.Currency;
import com.jaqm.bcp.service.CurrencyService;
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
    
    
    @GetMapping("")
    public Iterable<Currency> index() {
        return currencyService.getCurrencies();
    }
    
    @PostMapping(value = "/update", consumes = "application/json")
    public Currency update(@RequestBody Currency data) {
        return currencyService.update(data);
    }
    
    @GetMapping("/searchExchangeRate")
    public ExchangeRateDto searchExchangeRate(
            @RequestParam(value = "monto",required = true) Double monto,
            @RequestParam(value = "moneda_origen",required = true) String moneda_origen,
            @RequestParam(value = "moneda_destino",required = true) String moneda_destino
    ) {
        
        return currencyService.searchExchangeRate(monto,moneda_origen,moneda_destino);
    }
    
}
