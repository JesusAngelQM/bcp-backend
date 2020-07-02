/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaqm.bcp.controller;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import com.javainuse.service.JwtUserDetailsService;

import com.jaqm.bcp.config.JwtTokenUtil;
import com.jaqm.bcp.model.JwtRequest;
import com.jaqm.bcp.model.JwtResponse;
import com.jaqm.bcp.service.JwtUserDetailsService;

/**
 *
 * @author Jesus QM
 */
@RestController
@CrossOrigin
public class JwtAuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    //private JwtUserDetailsService userDetailsService;
    private JwtUserDetailsService jwtUserDetailsService;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = jwtUserDetailsService
        .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
        throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
        throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
