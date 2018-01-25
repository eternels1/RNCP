package com.loncoto.superMangaManiaSB.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.loncoto.superMangaManiaSB.metier.Role;
import com.loncoto.superMangaManiaSB.metier.Utilisateur;
import com.loncoto.superMangaManiaSB.repositories.RoleRepository;
import com.loncoto.superMangaManiaSB.repositories.UtilisateurRepository;

import lombok.extern.log4j.Log4j;


@Log4j(topic="DataBaseContentInitializer")@Service
public class DataBaseContentInitializer implements ApplicationListener<ContextRefreshedEvent> {

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
			Role r_visitor= roleRepository.save(new Role(0,"ROLE_VISITOR"));
			Role r_contributor= roleRepository.save(new Role(0,"ROLE_CONTRIBUTOR"));
			
			Utilisateur u1= new Utilisateur(0,"admin",myPasswordEncoder.encode("admin"),true);
			u1.getRoles().add(r_admin);
			u1.getRoles().add(r_visitor);
			utilisateurRepository.save(u1);
			
			Utilisateur u2= new Utilisateur(0,"khalid",myPasswordEncoder.encode("khalid"),true);
			u2.getRoles().add(r_visitor);
			utilisateurRepository.save(u2);
			
			Utilisateur u3= new Utilisateur(0,"contributeur",myPasswordEncoder.encode("admin"),true);
			u3.getRoles().add(r_contributor);
			utilisateurRepository.save(u3);
		}
		else {
			this.log.info("base has already users");
		}
		
		
	}
}
