package dev.gourab.listoperations.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class CollectionInputSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")).authorizeHttpRequests(auth -> {
			auth.requestMatchers("/api/search/**").permitAll().anyRequest().authenticated();
		}).formLogin(Customizer.withDefaults()).build();
	}

}