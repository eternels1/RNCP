package com.loncoto.firstSecurity.config;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.loncoto.firstSecurity.metier.Role;
import com.loncoto.firstSecurity.metier.Utilisateur;
import com.loncoto.firstSecurity.repositories.IInternalRepository;

@Service
public class DatabaseContentInitialiser implements ApplicationListener<ContextRefreshedEvent>{


	private static Logger log= LogManager.getLogger(DatabaseContentInitialiser.class);
	
	@Autowired
	private IInternalRepository internalRepository; 

	@Autowired
	private PasswordEncoder myPasswordEncoder;
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (internalRepository.countUsers()==0) {
			//pas de users, probablement base vide
			//on va donc creer des users par defaut et leur role
			log.info("base seem empty, initialising default content..");
			Role r_admin= internalRepository.createRole("ROLE_ADMIN");
			Role r_user= internalRepository.createRole("ROLE_USER");
			Role r_visitor= internalRepository.createRole("ROLE_VISITOR");
			
			Utilisateur u_Admin=internalRepository.createUser("admin", myPasswordEncoder.encode("admin"), r_admin,r_user);
			Utilisateur u_Vincent=internalRepository.createUser("Vincent", myPasswordEncoder.encode("1234"),r_user);
			Utilisateur u_Elon=internalRepository.createUser("Elon", myPasswordEncoder.encode("marslove"),r_visitor);			
			
		}
		else
			log.info("base isn't emplty, no initialisation required");
		
	}

	
}
