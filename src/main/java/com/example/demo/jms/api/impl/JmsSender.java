/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.jms.api.impl;

import com.example.demo.jms.api.JmsApi;
import com.example.demo.web.resource.AppUserResource;
import javax.jms.Destination;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author dima
 */
@Component("jmsSender")
public class JmsSender implements JmsApi {
    
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    
    private Destination destination = new ActiveMQQueue("testQueue");

    @Override
    public AppUserResource send(AppUserResource resource) {
        return jmsMessagingTemplate.convertSendAndReceive(destination, resource, AppUserResource.class);
    }

    @Override
    public AppUserResource receive(AppUserResource resource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
