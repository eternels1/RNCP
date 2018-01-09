package com.loncoto.firstBoot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.loncoto.firstBoot.metier.SpaceShip;

//@configuration nécessaire pour spécifier que cette classe est une classe de configuration
//de l'appli spring
@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {

	//config specifique au repository Spring data rest
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		
		
		super.configureRepositoryRestConfiguration(config);
		//demander a spring data rest de renvoyer la clé primaire avec le reste de l'objet dans le json 
		//pour les entité spécifier
		config.exposeIdsFor(SpaceShip.class);
	}
	

}
