package com.payday.login.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.requestMatchers()
			.antMatchers("/" , "/ui/**", "/login")
			.and()
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/ui/public/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().permitAll()
			;
		
	}
	
	public void configure(WebSecurity webSecurity) throws Exception{
		webSecurity
			.ignoring()
			.antMatchers("/js/**")
			.antMatchers("/css/**")
			;
	}

}
