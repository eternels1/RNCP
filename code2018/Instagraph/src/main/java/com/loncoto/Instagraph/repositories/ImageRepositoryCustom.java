package com.loncoto.Instagraph.repositories;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.loncoto.Instagraph.metier.Image;

public interface ImageRepositoryCustom {
	//sauvegarde du fichier uniquement
	boolean saveImageFile(Image img, InputStream f);
	Optional<File> getImageFile(String storageId);

	boolean deleteImageFile(Image image);
	
	//recherche d'images
	Page<Image> searchwithTags(List<Integer>includedTags, 
								List<Integer> excludedTags, 
								Pageable pageRequest);
}
