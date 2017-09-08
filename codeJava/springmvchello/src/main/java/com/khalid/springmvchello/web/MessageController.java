package com.khalid.springmvchello.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.khalid.springmvchello.metier.Message;
import com.khalid.springmvchello.repositories.ImessageDAO;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private ImessageDAO messageDAO;
	
	@RequestMapping(value="/liste",method= RequestMethod.GET)
	public ModelAndView liste() {
		ModelAndView mv= new ModelAndView("message/liste");
		mv.addObject("messages",messageDAO.findAll());
		return mv;
	}
	
	@RequestMapping(value="/details/{id:[0-9]+}", method=RequestMethod.GET)
	public ModelAndView details(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("message/details");
		Message msg= messageDAO.findById(id);
		if (msg==null) {
			mv.setViewName("redirect:/message/liste");
		}
		else {
			mv.addObject("message",msg);
		}
		return mv;
	}
	
	@RequestMapping(value="/titresearch",method=RequestMethod.POST)
	public ModelAndView seachrByTitre(@RequestParam("searchterm") String searchterm) {
		ModelAndView mv= new ModelAndView("message/liste");
		mv.addObject("messages",messageDAO.searchInTitre(searchterm));
		return mv;
	}
	
	
}
