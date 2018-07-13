/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import com.example.child.WebSecuredApplication;
import com.example.root.RootContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 *
 * @author dnikiforov
 */
public class MainStarter {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder();
		builder
				.parent(RootContext.class)
				.web(WebApplicationType.NONE)
				//.child(WebSecuredApplication.class);
				.run(args);
	}
	
}
