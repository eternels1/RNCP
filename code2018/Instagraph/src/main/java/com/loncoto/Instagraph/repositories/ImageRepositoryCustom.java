package com.loncoto.Instagraph.repositories;

import java.io.File;
import java.io.InputStream;
import java.util.Optional;

import com.loncoto.Instagraph.metier.Image;

public interface ImageRepositoryCustom {
	//sauvegarde du fichier uniquement
	boolean saveImageFile(Image img, InputStream f);
	Optional<File> getImageFile(String storageId);

	boolean deleteImageFile(Image image);
}
