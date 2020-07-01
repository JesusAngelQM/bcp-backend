package com.jaqm.bcp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author Jesus QM
 */

@Entity
@Data
public class Currency {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String codigoDivisa;
    
    private String nombre;
    
    private double tipoCambio;
    
}
