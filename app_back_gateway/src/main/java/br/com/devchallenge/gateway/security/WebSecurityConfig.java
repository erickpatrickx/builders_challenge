//package br.com.devchallenge.gateway.security;
//
//
//import java.util.Collections;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
///**
// * Config de segurança
// * @author erick.oliveira
// *
// */
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	
//	protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable().authorizeRequests()
//	      .anyRequest().permitAll()
//	      .and().formLogin().disable().httpBasic().disable();
//	}
//}
//	
