package com.jaqm.bcp.serviceImpl;

import com.jaqm.bcp.dto.ExchangeRateDto;
import com.jaqm.bcp.dto.CurrencyInterface;
import com.jaqm.bcp.model.Currency;
import com.jaqm.bcp.repository.CurrencyRepository;
import com.jaqm.bcp.service.CurrencyService;
import java.util.List;
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
    
    @Override
    public List<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }
    
    @Override
    public Currency update(Currency currency) {
        
        if ( !currencyRepository.existsById(currency.getId()) ) throw new UnsupportedOperationException("No existe tipo de cambio");
       
        return currencyRepository.save(currency);
    }

    @Override
    public ExchangeRateDto searchExchangeRate(Double monto, String moneda_origen, String moneda_destino) {
        
        List<CurrencyInterface> currencies = currencyRepository.findAllCurrencies();
        
        System.out.println("moneda_origen: "+moneda_origen);
        System.out.println("moneda_destino: "+moneda_destino);
        
        exchangeRate = new ExchangeRateDto();
        
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
        
        return exchangeRate;
        
    }

    
    
}
