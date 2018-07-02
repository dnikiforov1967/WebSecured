/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.jms.api;

import com.example.demo.jms.model.JmsResult;
import com.example.demo.web.resource.AppUserResource;

/**
 *
 * @author dima
 */
public interface JmsApi {
    
    JmsResult send(AppUserResource resource);
    
    JmsResult receive(AppUserResource resource);
    
}
