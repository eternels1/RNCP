package com.loncoto.todoliste.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import com.loncoto.todoliste.metier.Tache;
import com.loncoto.todoliste.repositories.TacheRepository;

@Controller
@RequestMapping(value="/")
public class IndexController {

	@Autowired
	private TacheRepository tacheRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirectToIndex() {
		return "home";
	}

	@RequestMapping(value="/taches", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<Tache> listeTache(@PageableDefault(page=0,size=5)Pageable page){
		return tacheRepository.findAll(page);
	}
	
	@RequestMapping(value="/taches/search/{search:.+}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<Tache> searchTache(@PageableDefault(page=0,size=5)Pageable page,
									@PathVariable("search") String search){
		
		return tacheRepository.findBylibelleContaining(search,page);
	}
	
	
	//////////////////////////bonus priorite
	
	@RequestMapping(value="/taches/searchPriorite/{priorite:[0-9]+}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<Tache> searchTachePriorite(@PageableDefault(page=0,size=5)Pageable page,
											@PathVariable("priorite") int priorite){
		
		return tacheRepository.findByprioriteGreaterThanEqual(priorite,page);
	}
	/////////////////////////
	
	
	
	@RequestMapping(value="/taches", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Tache saveTache (@RequestBody Tache tache) {
		return tacheRepository.save(tache);
	}
	
	@RequestMapping(value="/taches", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Tache updateTache (@RequestBody Tache tache) {
		Tache t= tacheRepository.findOne(tache.getId());
		if(t==null)
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"tache inconnu");
		return tacheRepository.save(tache);
	}
	
	@RequestMapping(value="/taches/{id:[0-9]+}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Tache findTache(@PathVariable("id") int id){
		Tache t = tacheRepository.findOne(id);
		if(t==null)
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"tache inconnu");
		return t;		
	}	
	
	@RequestMapping(value="/taches/{id:[0-9]+}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Tache deleteManga(@PathVariable("id") int id){
		Tache t = tacheRepository.findOne(id);
		if(t==null)
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"tache inconnu");
		
		tacheRepository.delete(t);
		return t;		
	}	
	
	
}