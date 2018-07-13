/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import com.example.child.WebSecuredApplication;
import com.example.common.InsectInterface;
import com.example.root.RootContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author dnikiforov
 */
public class MainStarter {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder();
		final ConfigurableApplicationContext run = builder
				.parent(RootContext.class)
				.web(WebApplicationType.NONE)
				.child(WebSecuredApplication.class)
				.web(WebApplicationType.SERVLET)
				.run(args);
		final InsectInterface bean = run.getBean(InsectInterface.class);
		System.out.println(bean.name());
		final ApplicationContext parent = run.getParent();
		final InsectInterface bean1 = parent.getBean(InsectInterface.class);
		System.out.println(bean1.name());
	}
	
}
