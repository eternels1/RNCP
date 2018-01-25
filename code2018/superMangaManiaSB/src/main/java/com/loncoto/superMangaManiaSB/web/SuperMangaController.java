package com.loncoto.superMangaManiaSB.web;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import com.loncoto.superMangaManiaSB.metier.ImageCouv;
import com.loncoto.superMangaManiaSB.metier.Manga;
import com.loncoto.superMangaManiaSB.metier.Role;
import com.loncoto.superMangaManiaSB.metier.Utilisateur;
import com.loncoto.superMangaManiaSB.repositories.RoleRepository;
import com.loncoto.superMangaManiaSB.repositories.SuperImageCouvRepository;
import com.loncoto.superMangaManiaSB.repositories.SuperMangaRepository;
import com.loncoto.superMangaManiaSB.repositories.UtilisateurRepository;


@Controller
@RequestMapping("/extendedapi/SuperMangaMania")
public class SuperMangaController {

	private static Logger log = LogManager.getLogger(SuperMangaController.class);
	
	@Autowired
	private SuperImageCouvRepository superImageCouvRepository;
	@Autowired
	private SuperMangaRepository superMangaRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private PasswordEncoder myPasswordEncoder;
	
	
	@CrossOrigin(origins="http://localhost:4200")@ResponseBody
	@RequestMapping(value="/SuperMangas",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)	
	@PreAuthorize("hasRole('ROLE_VISITOR')")
	public Page<Manga> listeSuperMangas(@PageableDefault(page=0, size=12) 
										Pageable page) {
		
		return superMangaRepository.findAll(page);
	}
		
	@CrossOrigin(origins="http://localhost:4200")@ResponseBody
	@RequestMapping(value="/upload/{mangaId:[0-9]+}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)	
	@PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_ADMIN')")
	public ImageCouv upload(@RequestParam("file") MultipartFile file,@PathVariable("mangaId")int mangaId) {
		
		log.info("file name  :" + file.getOriginalFilename());
		try {
			ImageCouv img = new ImageCouv(0,file.getOriginalFilename(), file.getContentType(),file.getSize(),"");
		
			superImageCouvRepository.saveImageFile(img, file.getInputStream());
			// le fichier est sauvegardé et img contient le storageId correspondant
			superImageCouvRepository.save(img);
			// ligne insérée dans la BDD
			Manga mangatempId= superMangaRepository.findOne(mangaId);
			mangatempId.setImgCouv(img);
			superMangaRepository.save(mangatempId);
			return img;
		} catch (IOException e) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
												"erreur a la sauvegarde");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/download/{id:[0-9]+}", method=RequestMethod.GET)
	@PreAuthorize("permitAll")
	public ResponseEntity<FileSystemResource> downloadImageData(@PathVariable("id") int id) {
		ImageCouv img = superImageCouvRepository.findOne(id);
		if (img == null)
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "image inconnue");
		// on recupere le fichier correspondant
		Optional<File> fichier = superImageCouvRepository.getImageFile(img.getStorageId());
		if (!fichier.isPresent())
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "fichier image introuvable");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(img.getContentType()));
		headers.setContentLength(img.getFileSize());
		headers.setContentDispositionFormData("attachment", img.getFileName());
		ResponseEntity<FileSystemResource> re =
				new ResponseEntity<FileSystemResource>(new FileSystemResource(fichier.get()),
													headers,
													HttpStatus.ACCEPTED);
		return re;
	}
	
	@ResponseBody
	@RequestMapping(value="/liaisonMangaImageCouv/{idSuperManga:[0-9]+}/{idImageCouv:[0-9]+}")
	public Manga liaisonMangaImageCouv(@PathVariable("idSuperManga") int idSuperManga,
										@PathVariable("idImageCouv") int idImageCouv) {
		Manga superManga=superMangaRepository.findOne(idSuperManga);
		ImageCouv img = superImageCouvRepository.findOne(idImageCouv);
		
		
		superManga.setImgCouv(img);
		return superMangaRepository.save(superManga);	
		 
	}
	
	//RESTE A FAIRE LA FONCTION DELETE
	//AVEC LE ROLE ADMIN POUR LE @PREAUTHORIZE
	@ResponseBody@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(value="/delete",
					method=RequestMethod.DELETE,
					produces=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Map<String, Object> deleteManga(@RequestParam("mangaId") int mangaId){
		
		Map<String, Object> result = new HashMap<>();
		Manga manga=superMangaRepository.findOne(mangaId);
		
		superMangaRepository.delete(manga.getId());
		
		int idCouv= manga.getImgCouv().getId();
		superImageCouvRepository.delete(idCouv);
		
		
		
		result.put("SuperManga_Supprime", manga.getTitre());
		result.put("IdCouv_Supprime", idCouv);
		
		return result;
		
	}
	
	

	
	
	
}
