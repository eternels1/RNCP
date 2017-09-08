package com.khalid.produitrest.web;

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

import com.khalid.produitrest.metier.Produit;
import com.khalid.produitrest.repositories.IProduitDAO;

@Controller
@RequestMapping("/api")
public class ProduitController {
	
	@Autowired
	private IProduitDAO produitDAO;

	@RequestMapping(value="/produits",method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<Produit> liste(){
		return produitDAO.findAll();
	} 
	
	@RequestMapping(value="/produits/searchtitre/{searchterm:.+}",method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<Produit> searchByTitle(@PathVariable("searchterm") String searchterm){
		return produitDAO.searchByName(searchterm);
	} 
	
	
	@RequestMapping(value="/produit/{id:[0-9]+}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public Produit findById(@PathVariable("id") int id) {
return produitDAO.findById(id);
}

@RequestMapping(value="/produit/{id:[0-9]+}",
			method=RequestMethod.DELETE,
			produces=MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public Map<String, Object> delete(@PathVariable("id") int id) {
produitDAO.delete(id);
Map<String, Object> result = new HashMap<>();
result.put("nbdeleted", 1);
return result;
}

@RequestMapping(value="/produit",
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public Produit save(@RequestBody Produit produit) {

return produitDAO.save(produit);
}


	
}
