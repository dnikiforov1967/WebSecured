package com.example.child;

import com.example.common.InsectInterface;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
		exclude = {DataSourceAutoConfiguration.class, JmsAutoConfiguration.class}
)
public class WebSecuredApplication {

	@Bean("mockito")
	public InsectInterface insectInterface() {
		return new InsectInterface() {
			@Override
			public String name() {
				return "Moskito 2";
			}
		};
	}

}
