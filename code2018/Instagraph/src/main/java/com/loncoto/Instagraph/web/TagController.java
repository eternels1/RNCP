package com.loncoto.Instagraph.web;

import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loncoto.Instagraph.metier.Tag;
import com.loncoto.Instagraph.repositories.ImageRepository;
import com.loncoto.Instagraph.repositories.TagRepository;

@Controller@RequestMapping("/extendedApi/tag")
public class TagController {

	private static Logger log = LogManager.getLogger(ImageController.class);
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private TagRepository tagRepository;
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(value="/liste", method=RequestMethod.GET,
					produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_USER')")
	public Page<Tag> findAll(@PageableDefault(page=0,size=15)Pageable page,
							@RequestParam("search")Optional<String> search){
		return tagRepository.findAll(page);
	}
}
