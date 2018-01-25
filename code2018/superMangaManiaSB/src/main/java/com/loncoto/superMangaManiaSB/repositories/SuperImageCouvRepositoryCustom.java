package com.loncoto.superMangaManiaSB.repositories;

import java.io.File;
import java.io.InputStream;
import java.util.Optional;

import com.loncoto.superMangaManiaSB.metier.ImageCouv;


public interface SuperImageCouvRepositoryCustom {

	// sauvegarde du fichier uniquement
		boolean saveImageFile(ImageCouv img, InputStream f);
		Optional<File> getImageFile(String storageId);
		boolean deleteImageFile(ImageCouv image);
}
