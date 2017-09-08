package com.khalid.epategallerie.repositories;

import java.io.File;
import java.io.InputStream;
import java.util.Optional;

public interface ImageFileRepositoryCustom {

	boolean saveImage(int id, InputStream data);
	Optional<File> getImage(int id);
	Optional<File> getImageThumb(int id);
	
}
