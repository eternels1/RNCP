package com.loncoto.Instagraph.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.loncoto.Instagraph.metier.Image;
import com.loncoto.Instagraph.web.ImageController;

//objet injectable dans d'autre bean spring
@Component
public class FileStorageManager {
	
	private static Logger log = LogManager.getLogger(FileStorageManager.class);
	private Random rd = new Random();
	
	@Value("${filestorage.base-repertoire}")
	private File storageRoot;//rep de base de sauvegarde

	public FileStorageManager() {
		super();
		log.info("demarrage du storage manager!");
	}
	
	public String saveNewFile(String collection,InputStream data) {
		if (storageRoot==null || !storageRoot.exists() || !storageRoot.isDirectory()) {
			throw new RuntimeException("storage root invalid");
		}
		String name= collection+"#"+rd.nextLong();
		String sha1Name= DigestUtils.sha1Hex(name);
		String sousRep = sha1Name.substring(0, 2);
		
		//on récupére un objet file sur le repertoire ou on stocke l'image 
		File rep=Paths.get(storageRoot.getAbsolutePath(),sousRep).toFile();
		//on créer le rep s'il n'existe pas déjà
		if (!rep.exists()) {
			rep.mkdirs();
		}
		if (!rep.isDirectory()) {
			throw new RuntimeException("unable to create storage directory for "+ sha1Name);
		}
			try {
				log.info("sauvegarde de "+ sha1Name);
				Files.copy(data, Paths.get(rep.getAbsolutePath(), sha1Name),
								StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				throw new RuntimeException("unable to save file",e);
			}					
		return sha1Name;		
	}
	
	public Optional<File> getImageFile(String storageId) {
		if (storageRoot==null || !storageRoot.exists() || !storageRoot.isDirectory()) {
			return Optional.empty();
		}
	
			//on récupére un objet file sur le repertoire ou on stocke l'image 
			File rep=Paths.get(storageRoot.getAbsolutePath(),storageId.substring(0, 2)).toFile();
			if (!rep.exists()|| !rep.isDirectory()) {
				return Optional.empty();
			}
			File f =Paths.get(rep.getAbsolutePath(), storageId).toFile();
			if (f!=null&&f.exists()&& f.isFile()) {
				return Optional.of(f);
				
			}
			
			else return Optional.empty();
	}
	
	public boolean deleteImageFile(String storageId) {
		if (storageRoot==null || !storageRoot.exists() || !storageRoot.isDirectory()) {
			return false;
		}
		
		//on récupére un objet file sur le repertoire ou on stocke l'image 
		File rep=Paths.get(storageRoot.getAbsolutePath(),storageId.substring(0, 2)).toFile();
		if (!rep.exists()|| !rep.isDirectory()) {
			return false;
		}
		
		File f =Paths.get(rep.getAbsolutePath(), storageId).toFile();
		if (f!=null&&f.exists()&& f.isFile()) {
			return f.delete();
			
		}
		
		else return false;
	}

}
