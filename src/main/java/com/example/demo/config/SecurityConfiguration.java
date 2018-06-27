/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.config;

import com.example.demo.security.handler.DaoUserDetailsService;
import com.example.demo.service.UserApiServiceInterface;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author dnikiforov
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final Logger LOG = Logger.getLogger(SecurityConfiguration.class.getName());

	@Autowired
	private UserApiServiceInterface userApiService;

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	private DaoAuthenticationProvider getDaoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider
				= new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(new DaoUserDetailsService(userApiService));
		return daoAuthenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf()
				//Disable CSRF support
				.disable()
				//adjust the matchers for request	
				.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/favicon.ico").permitAll()
				.antMatchers("/index.html").permitAll()
				.antMatchers("/security/login").permitAll()
				.anyRequest().authenticated()
				.and()
				//Adjust the logout behaviour
				.logout()
				.permitAll()
				.logoutRequestMatcher(new AntPathRequestMatcher("/security/login", "DELETE"))
				.logoutSuccessHandler(logoutSuccessHandler)
				.and()
				.getConfigurer(ExceptionHandlingConfigurer.class)
				//Exception handlers
				.accessDeniedHandler(accessDeniedHandler)
				.authenticationEntryPoint(authenticationEntryPoint);
		LOG.log(Level.WARNING, "Security HTTP changed");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getDaoAuthenticationProvider());
		LOG.log(Level.WARNING, "Security AuthenticationManagerBuilder changed");
	}

}
