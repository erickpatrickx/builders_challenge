package br.com.devchallenge.cliente.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.devchallenge.cliente.security.AuthenticationFilter;
import br.com.devchallenge.cliente.security.TokenAuthenticationService;
/**
 * Config de segurança
 * @author erick.oliveira
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final TokenAuthenticationService tokenAuthenticationService;

	public WebSecurityConfig() {
		super(true);
		tokenAuthenticationService = new TokenAuthenticationService();
	}


	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and().csrf().disable().authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/v2/api-docs",
                    "/configuration/ui",
                    "/swagger-resources",
                    "/configuration/security",
                    "/swagger-ui.html",
                    "/webjars/**",
                    "/api/v1/test/**"
					).permitAll().anyRequest().authenticated()
			       .and()
				   .addFilterBefore(new AuthenticationFilter(tokenAuthenticationService),UsernamePasswordAuthenticationFilter.class);

		}
	
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**");
    }

	
}
	
