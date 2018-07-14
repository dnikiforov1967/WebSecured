/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.root;

import com.example.common.InsectInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author dnikiforov
 */
@Configuration
@PropertySource("classpath:/root.properties") 
public class RootContext {

	@Bean("mockito")
	public InsectInterface insectInterface() {
		return new InsectInterface() {
			@Override
			public String name() {
				return "Mockito 1";
			}
		};
	}
	
}