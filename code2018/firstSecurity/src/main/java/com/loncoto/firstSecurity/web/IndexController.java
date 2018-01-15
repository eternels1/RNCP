package com.loncoto.firstSecurity.web;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller@RequestMapping("/")
public class IndexController {

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
}
