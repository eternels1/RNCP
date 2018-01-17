package com.loncoto.Instagraph.config;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.loncoto.Instagraph.metier.Role;
import com.loncoto.Instagraph.metier.Utilisateur;
import com.loncoto.Instagraph.repositories.RoleRepository;
import com.loncoto.Instagraph.repositories.UtilisateurRepository;

import lombok.extern.log4j.Log4j;

@Log4j(topic="DataBaseContentInitializer")@Service
public class DataBaseContentInitializer implements ApplicationListener<ContextRefreshedEvent> {

	//private static Logger log= LogManager.getLogger(DataBaseContentInitializer.class);
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder myPasswordEncoder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (utilisateurRepository.count()==0) {
			this.log.info("base seem empty, initialising default users and roles");
			Role r_admin= roleRepository.save(new Role(0,"ROLE_ADMIN"));
			Role r_user= roleRepository.save(new Role(0,"ROLE_USER"));
			
			Utilisateur u1= new Utilisateur(0,"admin",myPasswordEncoder.encode("admin"),true);
			u1.getRoles().add(r_admin);
			u1.getRoles().add(r_user);
			utilisateurRepository.save(u1);
			
			Utilisateur u2= new Utilisateur(0,"khalid",myPasswordEncoder.encode("khalid"),true);
			u2.getRoles().add(r_user);
			utilisateurRepository.save(u2);
		}
		else {
			this.log.info("base has already users");
		}
		
		
	}

}
