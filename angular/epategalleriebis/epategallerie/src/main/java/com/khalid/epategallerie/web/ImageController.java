package com.khalid.epategallerie.web;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.khalid.epategallerie.metier.ImageFile;
import com.khalid.epategallerie.metier.views.ImageComplete;
import com.khalid.epategallerie.metier.views.ImageWithoutTags;
import com.khalid.epategallerie.repositories.ImageFileRepository;

@Controller
@RequestMapping("/api")
public class ImageController {

	@Autowired
	private ImageFileRepository imageFileRepositories;
	
	@RequestMapping(value="/images", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@JsonView(ImageWithoutTags.class)
	public Iterable<ImageFile> liste(){
		return imageFileRepositories.findAll();
	}
	
	@RequestMapping(value="/imagesfull", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@JsonView(ImageComplete.class)
	public Iterable<ImageFile> listeFull(){
		return imageFileRepositories.findAllFull();
	}
	
	@RequestMapping(value="/images/serach/{searchterm:.+}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@JsonView(ImageComplete.class)
	public Iterable<ImageFile> searchByName(@PathVariable("searchterm") String searchterm){
		return imageFileRepositories.findByNameContaining(searchterm);
	}
	
	@RequestMapping(value="/imagesfull/serach/{searchterm:.+}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@JsonView(ImageComplete.class)
	public Iterable<ImageFile> searchFullByName(@PathVariable("searchterm") String searchterm){
		return imageFileRepositories.findFullByNameContaining("%"+searchterm+"%");
	}
	
	@RequestMapping(value="/images/upload", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@JsonView(ImageComplete.class)
	public ImageFile upload(@RequestParam("file") MultipartFile file) throws IOException {
		ImageFile img = new ImageFile(0, file.getOriginalFilename(), file.getOriginalFilename(), file.getContentType(), file.getSize(), "1234568746");
		img= imageFileRepositories.save(img);
		imageFileRepositories.saveImage(img.getId(), file.getInputStream());
		
		img.setHashMD5(DigestUtils.md5Hex(file.getInputStream()));
		
		img= imageFileRepositories.save(img);
		return img;
	}
	
	@RequestMapping(value="/images/download/{id:[0-9]+}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<FileSystemResource> downloadImage(@PathVariable("id") int id){
		ImageFile img= imageFileRepositories.findOne(id);
		if (img==null) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"image not found");
			}
		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentType(MediaType.parseMediaType(img.getContentType()));
		respHeaders.setContentLength(img.getFilsize());
		respHeaders.setContentDispositionFormData("attachement", img.getFileName());
		
		Optional<File> f = imageFileRepositories.getImage(id);
		if (f.isPresent()) {
			FileSystemResource fsr= new FileSystemResource(f.get());
			return new ResponseEntity<FileSystemResource>(fsr,respHeaders,HttpStatus.OK);
		}
		else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"image not found");
		}
	}
	
	@RequestMapping(value="/images/downloadthumb/{id:[0-9]+}",
			method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<FileSystemResource> donwloadImageThumb(@PathVariable("id") int id) {
		ImageFile img = imageFileRepositories.findOne(id);
		if (img == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,
												"image not found");
		}
		HttpHeaders respHeaders = new HttpHeaders();
		// image/jpeg image/gif etc	....
		// le navigateur en a besoin pour savoir quoi faire
		respHeaders.setContentType(
				MediaType.parseMediaType(img.getContentType()));
		// le nom original du fichier
		respHeaders.setContentDispositionFormData("attachment",
												img.getFileName());
		// on recupere le fichier physique
		Optional<File> f = imageFileRepositories.getImageThumb(id);
		if (f.isPresent()) {
			// si le fichier est bien la, on le renvoie
			FileSystemResource fsr = new FileSystemResource(f.get());
			return new ResponseEntity<FileSystemResource>(
													fsr,
													respHeaders,
													HttpStatus.OK);
		}
		else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,
												"file not found");
		}
	}
}
