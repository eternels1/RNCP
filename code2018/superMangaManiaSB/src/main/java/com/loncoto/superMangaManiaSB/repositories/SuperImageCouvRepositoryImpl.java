package com.loncoto.superMangaManiaSB.repositories;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.loncoto.superMangaManiaSB.metier.ImageCouv;
import com.loncoto.superMangaManiaSB.util.FileStorageManager;


public class SuperImageCouvRepositoryImpl implements SuperImageCouvRepositoryCustom {
	
	@Autowired
	private FileStorageManager fileStorageManager;

	@Override
	public boolean saveImageFile(ImageCouv img, InputStream f) {
				//-------------------------------------
				// sauvegarde image originale
				//----------------------------------
				String storageId = fileStorageManager.saveNewFile("images", f);
				img.setStorageId(storageId);				
				return true;
	}

	@Override
	public Optional<File> getImageFile(String storageId) {
		
		return fileStorageManager.getImageFile(storageId);
	}

	@Override
	public boolean deleteImageFile(ImageCouv image) {
		if (image == null)
			return false;
		boolean successA = fileStorageManager.deleteImageFile(image.getStorageId());
		return successA;
	}

}
