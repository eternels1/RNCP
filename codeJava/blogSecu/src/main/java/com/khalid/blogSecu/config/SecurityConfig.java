package com.khalid.blogSecu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// cette annotation indique a spring d'injecter ici un bean
		// déclar& dans le contexte spring
		@Autowired
		private DriverManagerDataSource dataSource;
		
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			// pour un jdbcAuthentication
			// il y a DEUX requettes minimales nécéssaires
			// A) requette permettant de récupérer un utilisateur
			//			a partir de son login
			//		cette requete "usersByUsernameQuery" doit renvoyer
			//		trois champs, le username, le password et enabled(true/false)
			// B) requette permettant de récupérer les droits
			//		d'un compte utilisateur (authorities)
			
			
			/*
			 * pour gerer l'authentification, c'est a dire vérifier
			 * l'identité d'une personne/access
			 * on configure l'AuthenticationManager
			 *  celui-ci est fournit par spring-security
			 *  avec quelques implémentations déjà présente
			 *  	-> inMemory -> en mémoire, uniquement pour test
			 *  	-> jdbcAuthentication, via des requetes jdbc
			 *  		a ses propres requettes que vous pouvez personnaliser
			 *  		fonctionne du moment que ces requettes renvoie les
			 *  		colonnes attendues
			 * 		-> UserDetailsService
			 * 			votre propre service custom de recupération
			 * 			d'utilisateur/authority
			 * il y en a d'autre, comme par exemple pour LDAP
			 * 
			 * au final cette authentiactionManager determine qui vous etes
			 * et vos droits, mais PAS si vous avez le droit d'acceder
			 * a une ressource (c'est le role du HTTPSecurity)
			 * 
			 */

			auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery(
	"select username, password, true as enabled from user where username=?")
				.authoritiesByUsernameQuery(
	"select username, rolename as authority from user" + 
	"	inner join user_role" + 
	"    on user.id =  user_role.users_id" + 
	"	inner join role" + 
	"    on user_role.roles_id = role.id" + 
	"	where user.username = ?")
				.groupAuthoritiesByUsername(
						"select username, 'none' as authority from user "
						+ " where 1=2 and username=?")
				.passwordEncoder(new PlaintextPasswordEncoder());
			
			/*
			 * ne pas stocker les mots de passe en "clair"
			 * 
			 * il faut les hasher
			 * 	meme entree ---> meme hash (spring security comparera
			 * 					la version hashée)
			 * dans la BDD, seul les hash de mot de passe sont stocké
			 * même si cette table est compromise, nous ne pouvons
			 * pas "simplement" retrouver le mot de passe original 
			 * 
			 *  fonction de hash très connue
			 *  	MD5  -> ne pas utiliser, vulnérable
			 *  	SHA(1,2,256) -> ok si pas trop critique
			 *      autres variantes
			 * 
			 * 
			 * 
			 */
			
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			/*
			 * pour authoriser des access aux url
			 *  ou utilise authorizeRequests
			 * 		sur lequel on peut declarer
			 * 	    un antmacthers("url format ant/apache")
			 * 			qui y a access:
			 * 				permitAll -> tous le monde
			 * 				anonymous -> non loggué
			 * 				authenticated -> quelqu'un de loggué
			 * 				hasRole -> quelqu'un qui a un role precis
			 * 				hasAuthority -> quelqu'un qui a un droit spécifique
			 * 				autre variantes....
			 * 
			 * ATTENTION, par defaut, spring security assumme, quand
			 * on demande la vérificcation d'un role (has_role)
			 * que le nom du role, dans la base, est prefixé par "ROLE_"
			 * 
			 */
			
			
			http.authorizeRequests().antMatchers("/blog/**")
								    .authenticated()
								    .antMatchers("/bloginterne/**")
								    .hasRole("USER")
								    .antMatchers("/admin")
								    .hasRole("ADMIN")
								    .antMatchers("/index")
								    .permitAll() //ou anonymous()
				.and().formLogin()
				.and().httpBasic();
		}

}
