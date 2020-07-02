package com.jaqm.bcp.serviceImpl;

import com.jaqm.bcp.dto.ExchangeRateDto;
import com.jaqm.bcp.dto.CurrencyInterface;
import com.jaqm.bcp.exception.NotFoundException;
import com.jaqm.bcp.model.Currency;
import com.jaqm.bcp.repository.CurrencyRepository;
import com.jaqm.bcp.service.CurrencyService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

/**
 *
 * @author Jesus QM
 */
@Service
public class CurrencyServiceImpl implements CurrencyService{

    ExchangeRateDto exchangeRate;
    
    @Autowired
    private CurrencyRepository currencyRepository;
    
    protected final Logger logger = LogManager.getLogger(getClass());
    
    @Override
    public List<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }
    
    @Override
    public Currency update(Currency currency) {
        
        boolean valor = currencyRepository.existsById(currency.getId());
        if ( valor == false ) throw new NotFoundException("No existe tipo de cambio");
       
        return currencyRepository.save(currency);
    }
    
    @Override
    public Currency store(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public ExchangeRateDto searchExchangeRate(Double monto, String moneda_origen, String moneda_destino) {
        
        List<CurrencyInterface> currencies = currencyRepository.findAllCurrencies();
        
        logger.info("moneda_origen: "+moneda_origen);
        logger.info("moneda_destino: "+moneda_destino);
        
        Observable.from(currencies)
                .filter(x -> (x.getMonedaOrigen().equals( moneda_origen ) && (x.getMonedaDestino().equals( moneda_destino ) )))
                /*.map(data ->{
                    System.out.println("data");
                    System.out.println(data.getMonedaOrigen());
                    System.out.println(data.getMonedaDestino());
                    return data;
                })*/
                .subscribe( data->{
                    System.out.println("convertir");
                    double tc = data.getTipoCambioDestino() / data.getTipoCambioOrigen();
                    double montoTipoCambio = monto * tc;
                    
                    exchangeRate = new ExchangeRateDto();
                    exchangeRate.setMonto(monto);
                    exchangeRate.setMontoTipoCambio(montoTipoCambio);
                    exchangeRate.setMonedaOrigen(data.getMonedaOrigen());
                    exchangeRate.setNombreOrigen(data.getNombreOrigen());
                    //current3.setTipoCambioOrigen(data.getTipoCambioOrigen());
                    exchangeRate.setMonedaDestino(data.getMonedaDestino());
                    exchangeRate.setNombreDestino(data.getNombreDestino());
                    //current3.setTipoCambioDestino(data.getTipoCambioDestino());
                    exchangeRate.setTipoCambio(tc);
                });
        
            if (exchangeRate==null) throw new NotFoundException("No existe resultados encontrados");
        
        return exchangeRate;
        
    }

    

    
    
}
