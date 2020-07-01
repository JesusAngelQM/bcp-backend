/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaqm.bcp.dto;

import lombok.Data;

/**
 *
 * @author Jesus QM
 */
@Data
public class ExchangeRateDto {
    
    private double monto;
    private double montoTipoCambio;
    
    private String monedaOrigen;
    private String nombreOrigen;
    //private double tipoCambioOrigen;
    private String monedaDestino;
    private String nombreDestino;
    //private double tipoCambioDestino;
    
    private double tipoCambio;

}
