package com.khalid.alloCine.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("khalid").password("P@ssw0rd").roles("USER").and()
			.withUser("admin").password("1234").roles("USER","ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/film/**")
								.authenticated()
								.antMatchers("/films")
								.hasRole("USER")
								.antMatchers("/admin")
								.hasRole("ADMIN")
								.antMatchers("/","/restfilms")
								.permitAll()
					.and().formLogin().and().httpBasic();
	}
	
	

}
