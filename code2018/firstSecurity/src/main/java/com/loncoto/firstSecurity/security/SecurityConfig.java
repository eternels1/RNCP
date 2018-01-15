package com.loncoto.firstSecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// le parametre auth passé ici permet de configurer tous ce qui concerne l'authentification gestion 
		// des comptes utilisateurs/identités, cet objet utilise le pattern builder
		
		
		//exemple basique avec utilisateur en "dur" stocké en mémoire
		/*auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN","USER").and()
										.withUser("khalid").password("khalid").roles("USER").and()
										.withUser("elon").password("marslove").roles("VISOTOR");*/
		
		
		//exemple basique avec utilisateur via BDD
		
		//1) via jdbc classique
		/*auth.jdbcAuthentication().usersByUsernameQuery("sql..")//username, password, enabled
								 .groupAuthoritiesByUsername("sql...");//username, authorities*/
		
		//2) on veut récupérer nos utilisateurs/roles via jpa(entites)
		//fournir notre propre service de récupération des utilisateurs et / ou roles
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(new PlaintextPasswordEncoder());//ATTENTION A NE PAS FAIRE EN VRAI
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// l'objet http paséé en parametre permet de configurer les droits d'accès et plein d'autre choses
		// (gestion du login, session, cors, csrf etc...
		
		//meme design pattern qu'au dessus, le patern builder
	http.authorizeRequests().antMatchers("/admin")
							.hasRole("ADMIN")
							.antMatchers("/client").hasAnyRole("ADMIN","USER")
							.antMatchers("/public")
							.authenticated()
							.antMatchers("/").permitAll()
				.and().httpBasic()
				.and().csrf().disable();
	}

}
