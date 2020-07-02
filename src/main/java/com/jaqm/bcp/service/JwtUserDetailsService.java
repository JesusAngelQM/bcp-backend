/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaqm.bcp.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jesus QM
 */
@Service
public class JwtUserDetailsService implements UserDetailsService{

    @Value("${jwt.username}")
    private String username;
    
    @Value("${jwt.password}")
    private String password;
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(this.username,this.password,new ArrayList<>());
    }
    
}
