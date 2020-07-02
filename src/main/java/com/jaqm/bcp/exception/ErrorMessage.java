/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaqm.bcp.exception;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author Jesus QM
 */
@Data
public class ErrorMessage {
    private Date timestamp;
    private String message;

    public ErrorMessage(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }
    
}
