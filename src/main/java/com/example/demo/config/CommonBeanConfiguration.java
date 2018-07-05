/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.config;

import com.example.demo.beans.TicketInterface;
import com.example.demo.beans.impl.Ticket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 *
 * @author dnikiforov
 */
@Configuration
public class CommonBeanConfiguration {
	
	@Bean
	@Scope(value = "prototype", proxyMode = ScopedProxyMode.INTERFACES)
	public TicketInterface ticketInterface() {
		return new Ticket();
	}
	
}
