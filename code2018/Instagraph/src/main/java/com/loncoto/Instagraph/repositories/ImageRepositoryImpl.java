package com.loncoto.Instagraph.repositories;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.loncoto.Instagraph.metier.Image;
import com.loncoto.Instagraph.util.FileStorageManager;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.resizers.configurations.Antialiasing;
import net.coobird.thumbnailator.resizers.configurations.ScalingMode;

public class ImageRepositoryImpl implements ImageRepositoryCustom {

	@Autowired
	private FileStorageManager fileStorageManager;
	private Logger log = LogManager.getLogger(ImageRepositoryCustom.class);
	
	public static final int THUMB_WIDTH=164;
	public static final int THUMB_HEIGHT=164;
	
	@Override
	public boolean saveImageFile(Image img, InputStream f) {
	
		//-------------------------------------------
		//sauvegarde image originale
		//-------------------------------------------
		String storageId= fileStorageManager.saveNewFile("images", f);
		//-------------------------------------------
		//génération et sauvegarde miniature
		//-------------------------------------------
		ByteArrayOutputStream bos= new ByteArrayOutputStream();
		
		try {
			Thumbnails.of(fileStorageManager.getImageFile(storageId).get())
						.size(THUMB_WIDTH, THUMB_HEIGHT)
						.scalingMode(ScalingMode.BICUBIC)
						.antialiasing(Antialiasing.ON)
						.outputFormat("jpg")
						.toOutputStream(bos);
			//sauvegarde de la miniature en fichier
			String thumbStorageId= fileStorageManager.saveNewFile("imagesThumb", 
					new ByteArrayInputStream(bos.toByteArray()));
			//Mise a jour objet image avec référence thumbnail
			img.setThumbStorageId(thumbStorageId);
			
		} catch (IOException | ArrayIndexOutOfBoundsException e) {
			log.error("erreur à la génération miniature "+e);
		}
		
		
		img.setStorageId(storageId);
		return true;
	}

	@Override
	public Optional<File> getImageFile(String storageId) {
		
		return fileStorageManager.getImageFile(storageId);
	}

	@Override
	public boolean deleteImageFile(Image image) {
		if (image==null) 
		{
			return false;
		}
		
		boolean successA= fileStorageManager.deleteImageFile(image.getStorageId());
		boolean successB= fileStorageManager.deleteImageFile(image.getThumbStorageId());
		
		return successA && successB;
	}

	
	
	//METHODE DE RECHERCHE
	@PersistenceContext
	private EntityManager em;
	
	
	@Override@Transactional(readOnly=true)
	public Page<Image> searchwithTags(List<Integer> includedTags, List<Integer> excludedTags,
										Pageable pageRequest) {
		StringBuilder sb= new StringBuilder("from Image as img");
		if (!includedTags.isEmpty()) {
			StringBuilder sbJoin=new StringBuilder();
			StringBuilder sbWhere=new StringBuilder(" WHERE ");
			
			for(int position=1; position<= includedTags.size();position++) {
				sbJoin.append(", In(img.tags) ta" +position);//", IN(img.tags) ta1..."
				//sbJoin.append(", JOIN img.tags as ta" +position);//", IN(img.tags) ta1..."				
				if (position>1) {
				sbWhere.append(" AND ");
				}
				sbWhere.append("ta"+position).append(".id=:tincid"+position);
			}
			sb.append(sbJoin);
			sb.append(sbWhere);
		}
		log.info("reuette générée : "+sb.toString());
		//création de la requette
		TypedQuery<Image> query=em.createQuery("select img "+sb.toString(),Image.class);
		TypedQuery<Long> countQuery=em.createQuery("select count(img) "+sb.toString(),Long.class);
		for(int position=1; position<= includedTags.size();position++) {
			query.setParameter("tincid"+position, includedTags.get(position-1));
			countQuery.setParameter("tincid"+position, includedTags.get(position-1));
		}
		//pagination de la requette
		query.setFirstResult(pageRequest.getOffset());//position de démarrage de la requette
		query.setMaxResults(pageRequest.getPageSize());//combien d'image renvoyer
		
		//retourn la page
		return new PageImpl<>(query.getResultList(),pageRequest,countQuery.getSingleResult());
	}

}
