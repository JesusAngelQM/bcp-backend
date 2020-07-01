package com.jaqm.bcp.service;

import com.jaqm.bcp.dto.ExchangeRateDto;
import com.jaqm.bcp.model.Currency;
import java.util.List;


public interface CurrencyService {
    
    public List<Currency> getCurrencies();
    public Currency update(Currency currency);
    public ExchangeRateDto searchExchangeRate(Double monto, String moneda_origen, String moneda_destino);
        
}
