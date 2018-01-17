package com.loncoto.firstSecurity.validation;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.loncoto.firstSecurity.metier.Utilisateur;


//un validateur d'objet Utilisateur
//Attention, le nom de la classe est IMPORTANT
//de meme le beforsave est important
//ici beforSave-->avant sauvegarde en base
//Utilisateur --> pour les entité Utilisateur
@Component(value="beforeCreateUtilisateurValidator")
public class UtilisateurValidator implements Validator {

	private static Logger log= LogManager.getLogger(UtilisateurValidator.class);
	//cette méthode sera appeler par spring pour vérifier 
	//si ce validateur s'applique bien à l'entité a valider
	@Override
	public boolean supports(Class<?> clazz) {
		// je ne valide que les entité de classe Utilisateur
		return Utilisateur.class.equals(clazz);
	}

	//target -> l'entité à vérifier valider (ici notre Utilisateur)
	//errors -> objet contenant la liste des erreurs de validation, à destination
	//du framwork de validation
	@Override
	public void validate(Object target, Errors errors) {
		log.info("validation objet " + target);
		Utilisateur u= (Utilisateur)target;
		String name=u.getUserName();
		if (name==null || name.length()<3 || name.length()>100) {
			errors.rejectValue("userName", "name is empty or too short or too long");
		}
		

	}

}
