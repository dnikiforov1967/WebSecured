/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.jms.api.impl;

import com.example.demo.jms.api.JmsApi;
import com.example.demo.jms.model.JmsResult;
import com.example.demo.jpa.model.AppUser;
import com.example.demo.service.UserApiServiceInterface;
import com.example.demo.web.resource.AppUserResource;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author dima
 */
@Component("jmsConsumer")
public class JmsConsumer implements JmsApi {

	@Autowired
	@Qualifier("jpaUserApiService")
	private UserApiServiceInterface userApi;
	
    @Override
    public JmsResult send(AppUserResource resource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @JmsListener(destination = "testQueue", containerFactory = "custimizedFactory")
    //@Transactional
    public JmsResult receive(AppUserResource resource) {
		try {
			final AppUser user = AppUserResource.CONVERT2USER(resource);
			final AppUser created = userApi.create(user);
			AppUserResource result = new AppUserResource(created);
			return new JmsResult(result,null);
		} catch(Exception ex) {
			return new JmsResult(null, ex);
		}	
    }
    
}
