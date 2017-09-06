package com.khalid.visageLivre.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khalid.visageLivre.metier.Profil;
import com.khalid.visageLivre.repositories.IProfilDAO;

@Controller
@RequestMapping("/api")
public class ProfilController {

	@Autowired
	private IProfilDAO profilDAO;
	
	@RequestMapping(value="/profils",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Profil> liste(){
		return profilDAO.findAll();
	}
	
	@RequestMapping(value="/profils/searchname/{searchterm:.+}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Profil> searchByName(@PathVariable("searchterm") String searchterm){
		return profilDAO.searchByName(searchterm);
	}
	
	@RequestMapping(value="/profils/searchtown/{searchTermVille:.+}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Profil> searchByTown(@PathVariable("searchTermVille") String searchTermVille){
		return profilDAO.searchByTown(searchTermVille);
	}
	
	@RequestMapping(value="/profils/searchnameandtown/{searchTermName:.+}/{searchTermVille:.+}",
					method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Profil> searchByNameAndTown(@PathVariable("searchTermName") String searchTermName, 
											@PathVariable("searchTermVille") String searchTermVille){
		return profilDAO.searchByNameAndTown(searchTermName,searchTermVille);
	}
	
	@RequestMapping(value="/profil/{id:[0-9]+}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Profil findById(@PathVariable("id") int id) {
		return profilDAO.findById(id);
	}
	
	@RequestMapping(value="/profil/{id:[0-9]+}",method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("id") int id){
		profilDAO.delete(id);
		Map<String, Object> result= new HashMap<>();
		result.put("nbdeleted", 1);
		return result;
	}
	
	@RequestMapping(value="/profil",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Profil save(@RequestBody Profil profil) {
		return profilDAO.save(profil);
	}

}

