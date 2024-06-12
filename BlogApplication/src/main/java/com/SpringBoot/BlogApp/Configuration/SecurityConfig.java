package com.SpringBoot.BlogApp.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		 httpSecurity.csrf(csrf -> csrf.disable()) 
		 .authorizeHttpRequests((authorize) -> authorize
					 .requestMatchers("/**").permitAll()
					 .requestMatchers("/v3/api-docs").permitAll()
					 .anyRequest().authenticated()
			    );
		 
		 
		 return httpSecurity.build();
	 } 
}
