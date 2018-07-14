package com.example.child;

import com.example.common.InsectInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
		exclude = {DataSourceAutoConfiguration.class, JmsAutoConfiguration.class}
)
public class WebSecuredApplication {
    
        @Autowired
        private ApplicationContext context;
        
	@Bean("mockitoParent")
	public InsectInterface insectInterfaceParent() {
		return context.getParent().getBean("mockito", InsectInterface.class);
	}        

	@Bean("mockito")
	public InsectInterface insectInterface() {
		return new InsectInterface() {
			@Override
			public String name() {
				return "Mockito 2";
			}
		};
	}

}
