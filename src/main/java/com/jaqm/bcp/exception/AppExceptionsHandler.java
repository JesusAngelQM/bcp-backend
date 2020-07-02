/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaqm.bcp.exception;

import java.util.Date;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Jesus QM
 */
@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNullPointerException(NotFoundException ex, WebRequest request){
        String errorMessageDescription = ex.getLocalizedMessage();
        if(errorMessageDescription==null) errorMessageDescription = ex.toString();
        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
        
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
