package com.khalid.epategallerie.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileStorageManager {

	private static Logger log=LogManager.getLogger(FileStorageManager.class);
	
	private File storageRoot;
	public File getStorageRoot() {return storageRoot;}
	public void setStorageRoot(File storageRoot) {this.storageRoot = storageRoot;}
	public FileStorageManager() {}
	
	public boolean saveFile(String entityName, int id, InputStream data) {
		if (storageRoot==null || !storageRoot.exists() || !storageRoot.isDirectory()) {
			log.error("pas de répertoire où stocker les fichiers");
			return false;
		}
		String md5name=DigestUtils.md5Hex(entityName);
		File entityRep= new File(storageRoot.getAbsolutePath()+File.separatorChar+md5name);
	
		if (!entityRep.exists()) {
			entityRep.mkdirs();
		}
		
		if (!entityRep.isDirectory()) {
			log.error("le répertoire pour cette entité n'est pas accéssible");
			return false;
		}
		
		String filename= DigestUtils.md5Hex(entityName+"#"+id);
		try {
			Files.copy(data, Paths.get(entityRep.getAbsolutePath(),filename),StandardCopyOption.REPLACE_EXISTING);
			return true;
		} catch (IOException e) {
			log.error(e);
		}
		
		return false;
	}
	
	public Optional<File> getFile(String entityName, int id){
		if (storageRoot==null || !storageRoot.exists() || !storageRoot.isDirectory()) {
			log.error("pas de répertoire où stocker les fichiers");
			return Optional.empty();
		}
		String md5name=DigestUtils.md5Hex(entityName);
		File entityRep= new File(storageRoot.getAbsolutePath()+File.separatorChar+md5name);
	
		if (!entityRep.exists()) {
			return Optional.empty();
		}
		
		if (!entityRep.isDirectory()) {
			log.error("le répertoire pour cette entité n'est pas accéssible");
			return Optional.empty();
		}
		
		String filename= DigestUtils.md5Hex(entityName+"#"+id);
		File f= new File(entityRep.getAbsolutePath()+File.separatorChar+filename);
		
		if (f.exists()&&f.isFile()) {
			return Optional.of(f);
		}
		else 
		return Optional.empty();
	}
	
	
}
