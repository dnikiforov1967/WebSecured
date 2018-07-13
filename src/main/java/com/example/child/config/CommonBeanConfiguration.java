/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.child.config;

import com.example.child.aop.SimpleAdvice;
import com.example.child.beans.LightningInterface;
import com.example.child.beans.MetallInterface;
import com.example.child.beans.ThunderInterface;
import com.example.child.beans.TicketInterface;
import com.example.child.beans.impl.Lightning;
import com.example.child.beans.impl.Thunder;
import com.example.child.beans.impl.Ticket;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.CommonsPool2TargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 *
 * @author dnikiforov
 */
@Configuration
@EnableAspectJAutoProxy
public class CommonBeanConfiguration {

	@Autowired
	private SimpleAdvice simpleAdvice;
	
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
		proxyFactoryBean.addAdvice(simpleAdvice);
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
	
	@Bean(name="cooper")
	public MetallInterface metallInterface1() {
		return new MetallInterface() {
			@Override
			public void name() {
				System.out.println("Cooper 1");
			}
			
		};
	}
	
	@Bean(name="cooper")
	public MetallInterface metallInterface2() {
		return new MetallInterface() {
			@Override
			public void name() {
				System.out.println("Cooper 2");
			}
			
		};
	}

}
