package com.khalid.epategallerie.repositories;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.khalid.epategallerie.utils.FileStorageManager;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.resizers.configurations.Antialiasing;
import net.coobird.thumbnailator.resizers.configurations.ScalingMode;

public class ImageFileRepositoryImpl implements ImageFileRepositoryCustom {

	@Autowired
	private FileStorageManager fileStorageManager;
	
	@Override
	public boolean saveImage(int id, InputStream data) {
		if (fileStorageManager.saveFile("imageFile", id, data)) {
			try {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				File f = fileStorageManager.getFile("imageFile", id).get();
				Thumbnails.of(f)
						  .size(128, 128)
						  .scalingMode(ScalingMode.BICUBIC)
						  .antialiasing(Antialiasing.ON)
						  .toOutputStream(bos);
				return fileStorageManager.saveFile(
							"imageFileThumb",
							id,
							new ByteArrayInputStream(bos.toByteArray()));
			} catch (IOException e) {e.printStackTrace();}
		}
		return false;
	}

	@Override
	public Optional<File> getImage(int id) {
		
		return fileStorageManager.getFile("imageFile", id);
	}
	
	@Override
	public Optional<File> getImageThumb(int id) {
		return fileStorageManager.getFile("imageFileThumb", id);
	}

}
