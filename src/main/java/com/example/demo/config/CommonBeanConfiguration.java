/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.config;

import com.example.demo.beans.LightningInterface;
import com.example.demo.beans.ThunderInterface;
import com.example.demo.beans.TicketInterface;
import com.example.demo.beans.impl.Lightning;
import com.example.demo.beans.impl.Thunder;
import com.example.demo.beans.impl.Ticket;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.CommonsPool2TargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
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

	@Bean(name = "pooledLightning")
	@Scope(value = "prototype")
	public LightningInterface getLightning() {
		return new Lightning();
	}

	@Bean(name = "lightningTargetSource")
	public CommonsPool2TargetSource getTargetSource() {
		CommonsPool2TargetSource targetSource = new CommonsPool2TargetSource();
		targetSource.setBlockWhenExhausted(true);
		targetSource.setMinIdle(2);
		targetSource.setMaxSize(10);
		targetSource.setTimeBetweenEvictionRunsMillis(60000);
		targetSource.setMaxIdle(5);
		targetSource.setTargetClass(LightningInterface.class);
		targetSource.setTargetBeanName("pooledLightning");
		return targetSource;
	}

	@Bean(name = "lightningFactoryBean")
	public ProxyFactoryBean lightningFactoryBean(@Autowired @Qualifier("lightningTargetSource") CommonsPool2TargetSource targetSource) {
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		proxyFactoryBean.setTargetSource(targetSource);
		return proxyFactoryBean;
	}

	@Bean
	public LightningInterface lightningInterface(@Autowired @Qualifier("lightningFactoryBean") ProxyFactoryBean proxyFactoryBean) {
		return (LightningInterface) proxyFactoryBean.getObject();
	}

	@Bean
	@Scope(value = "prototype", proxyMode = ScopedProxyMode.INTERFACES)
	public TicketInterface ticketInterface() {
		return new Ticket();
	}

	@Bean
	@Scope(value = "singleton")
	public ThunderInterface thunderInterface() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Thunder.class);
		enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
			final Thunder thunder = new Thunder();
			return proxy.invoke(thunder, args);
		});
		return (ThunderInterface) enhancer.create();
	}

}
