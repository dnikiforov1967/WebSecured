package com.example.child;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;

@SpringBootApplication(
		exclude = {DataSourceAutoConfiguration.class, JmsAutoConfiguration.class}
)
public class WebSecuredApplication {

}
