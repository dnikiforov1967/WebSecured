/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.jms.api.impl;

import com.example.demo.jms.api.JmsApi;
import com.example.demo.web.resource.AppUserResource;
import javax.transaction.Transactional;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author dima
 */
@Component("jmsConsumer")
public class JmsConsumer implements JmsApi {

    @Override
    public AppUserResource send(AppUserResource resource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @JmsListener(destination = "testQueue", containerFactory = "custimizedFactory")
    @Transactional
    public AppUserResource receive(AppUserResource resource) {
        return resource;
    }
    
}
