package com.loncoto.firstSecurity.web;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loncoto.firstSecurity.metier.Role;
import com.loncoto.firstSecurity.metier.Utilisateur;
import com.loncoto.firstSecurity.repositories.IInternalRepository;
import com.loncoto.firstSecurity.repositories.RoleRepository;
import com.loncoto.firstSecurity.repositories.UtilisateurRepository;

@Controller@RequestMapping("/")
public class IndexController {
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private IInternalRepository internalRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private PasswordEncoder myPasswordEncoder;

	@RequestMapping(value="/",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> index(){
		Map<String, Object> result= new HashMap<>();
		result.put("message", "vous étes sur la pasge d'acceuil");
		result.put("date", new Date());
		return result;
	}
	
	
		@RequestMapping(value="/public",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public Map<String, Object> indexPublic(){
			Map<String, Object> result= new HashMap<>();
			result.put("message", "vous étes sur la pasge public");
			result.put("date", new Date());
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			result.put("utilisateur", auth.getName());
			result.put("autorities", auth.getAuthorities().stream().map(aut-> aut.getAuthority())
			.collect(Collectors.toList()));
			return result;
		}
		
		
		

			@RequestMapping(value="/client",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
			@ResponseBody
			public Map<String, Object> indexClient(Principal principal){
				Map<String, Object> result= new HashMap<>();
				result.put("message", "vous étes sur la pasge client");
				result.put("date", new Date());
				result.put("utilisateur", principal.getName());
				return result;
			}
			
			
				@RequestMapping(value="/admin",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
				@ResponseBody
				public Map<String, Object> indexAdmin(Authentication authentication){
					Map<String, Object> result= new HashMap<>();
					result.put("message", "vous étes sur la pasge admin");
					result.put("date", new Date());
					result.put("utilisateur", authentication.getName());
					result.put("", authentication.getAuthorities().stream().map(aut-> aut.getAuthority())
							.collect(Collectors.toList()));
					return result;
				}
				
				
				@PreAuthorize("hasRole('ROLE_VISITOR')")
				@RequestMapping(value="/toto",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
				@ResponseBody
				public Map<String, Object> toto(){
					Map<String, Object> result= new HashMap<>();
					result.put("message", "vous étes sur la pasge toto");
					result.put("date", new Date());
					return result;
				}
				
				
				
				@RequestMapping(value="/register",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
				@ResponseBody
				public Utilisateur register(@RequestParam("username")String username,
											@RequestParam("password")String password) {
					
					Role r = roleRepository.findByRoleName("ROLE_USER");
					
					//Utilisateur u= new Utilisateur(0, username,myPasswordEncoder.encode(password), true);
					
					Utilisateur u=internalRepository.createUser(username, myPasswordEncoder.encode(password),r);
					
					return u;
				}
}
