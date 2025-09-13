package com.springapp.app.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ExpenseTrackerSecurityConfiguration {

	@Autowired
	private TrackerAuthenticationEntryPoint entryPoint;
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
			.authorizeHttpRequests(auth-> auth
					.requestMatchers(HttpMethod.POST,"/users/**").permitAll()
					.requestMatchers("/users/all").permitAll()
					.anyRequest()
					.authenticated())
			.csrf(csrf->csrf.disable())
			.exceptionHandling(ex->ex.authenticationEntryPoint(entryPoint))
			.httpBasic(Customizer.withDefaults());
		return http.build();
	}
}
