package com.loncoto.Instagraph.web;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import com.loncoto.Instagraph.metier.Image;
import com.loncoto.Instagraph.metier.projections.ImageWithTags;
import com.loncoto.Instagraph.repositories.ImageRepository;
import com.loncoto.Instagraph.util.FileStorageManager;

@Controller@RequestMapping("/extendedApi/image")
public class ImageController {
	
	private static Logger log = LogManager.getLogger(ImageController.class);
	@Autowired
	private ImageRepository imageRepository;
	private final ProjectionFactory projectionFactory;
	
	@Autowired
	public ImageController(ProjectionFactory projectionFactory) {
		this.projectionFactory=projectionFactory;

	}
	
		
	@RequestMapping(value="/upload",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody@CrossOrigin(origins="http://localhost:4200")
	public Image upload(@RequestParam("file")MultipartFile file) {
		log.info("filename :" +file.getOriginalFilename());
		log.info("content type :"+file.getContentType());
		try {
		Image img = new Image(0,
				file.getOriginalFilename(),
				"",
				LocalDateTime.now(),
				file.getOriginalFilename(),
				file.getContentType(),
				file.getSize(),				
				0,
				0,
				DigestUtils.sha1Hex(file.getInputStream()),//somme de controle de fichier
				"",
				"");
		
			
		imageRepository.saveImageFile(img, file.getInputStream());		
		//le fichier est sauvegarder et img contient le storageId correspondant
		imageRepository.save(img);
		//ligne insérée dans la base de donnée
		return img;
			
		
		} catch (IOException e) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
					"erreur a la sauvegarde");
		}		
	}
	
	@RequestMapping(value="/download/{id:[0-9]+}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<FileSystemResource> imageData(@PathVariable("id")long id){
		Image img = imageRepository.findOne(id);
		if (img==null) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"image inconnue");
		}
		Optional<File> fichier= imageRepository.getImageFile(img.getStorageId());
		
		if (!fichier.isPresent()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"fichier image introuvale");
		}
		
		ResponseEntity<FileSystemResource> re;
		
			HttpHeaders headers= new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType(img.getContentType()));
			headers.setContentLength(img.getFileSize());
			headers.setContentDispositionFormData("attachement", img.getFileName());
			
			re = new ResponseEntity<FileSystemResource>(new FileSystemResource(fichier.get()),
																		headers,
																		HttpStatus.ACCEPTED);
		
		return re;
	}
	
	
	@RequestMapping(value="/downloadThumb/{id:[0-9]+}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<FileSystemResource> imageDataThumb(@PathVariable("id")long id){
		Image img = imageRepository.findOne(id);
		if (img==null) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"image inconnue");
		}
		Optional<File> fichier= imageRepository.getImageFile(img.getThumbStorageId());
		
		if (!fichier.isPresent()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"fichier image introuvale");
		}
		
		ResponseEntity<FileSystemResource> re;
		
			HttpHeaders headers= new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			headers.setContentLength(fichier.get().length());
			headers.setContentDispositionFormData("attachement", img.getFileName());
			
			re = new ResponseEntity<FileSystemResource>(new FileSystemResource(fichier.get()),
																		headers,
																		HttpStatus.ACCEPTED);
		
		return re;
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(value="/findbytag", method=RequestMethod.GET,
					produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<Image> findByTags(@RequestParam("tagsId") Optional <List<Integer>> tagsId,
									@PageableDefault(page=0,size=12)Pageable page){
		if (tagsId.isPresent()) {
			log.info("tagsId= "+ tagsId.get().toString());
		}
		else
		log.info("pas de tags en parametres");
		
		return imageRepository.findAll(page);
	}
	
	
	@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(value="/findbytagfull", method=RequestMethod.GET,
					produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<ImageWithTags> findByTagsFull(@RequestParam("tagsId") Optional <List<Integer>> tagsId,
									@PageableDefault(page=0,size=12)Pageable page){
		if (tagsId.isPresent()) {
			log.info("tagsId= "+ tagsId.get().toString());
			return imageRepository.searchwithTags(tagsId.get(), null, page)
									.map(img-> projectionFactory.createProjection(ImageWithTags.class,img));
			
		}
		else
		log.info("pas de tags en parametres");
		
		return imageRepository.findAll(page).map(img-> projectionFactory.createProjection(ImageWithTags.class,img));
	}
	
	
	
	
	
	@ResponseBody@CrossOrigin(origins="http://localhost:4200")
	@RequestMapping(value="/delete",
					method=RequestMethod.DELETE,
					produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> deleteImages(@RequestParam("imagesId") List<Long> imagesId){
		
		Map<String, Object> result = new HashMap<>();
		Iterable<Image> images=imageRepository.findAll(imagesId);
		//efface les images dans la bdd
		imageRepository.delete(images);
		
		int nbImagesToDelete=0;
		int nbFilesDeleted=0;
		//efface les fichiers immages correspondants
		for (Image img : images) {
			nbImagesToDelete++;
			if(imageRepository.deleteImageFile(img))
				nbFilesDeleted++;
		}
		
		//retourner les infos sur ce qui a été fait
		result.put("nbToDelete", nbImagesToDelete);
		result.put("nbFilesDeleted", nbFilesDeleted);
			
		return result;

	}
	
	
	
}
