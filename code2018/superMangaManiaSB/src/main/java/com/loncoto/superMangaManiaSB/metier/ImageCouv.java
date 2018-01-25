package com.loncoto.superMangaManiaSB.metier;



import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@NoArgsConstructor
@Entity
public class ImageCouv {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		private String fileName;
		private long fileSize;		
		@Column(length=60) 						
		private String storageId;
		@Column(length=100)
		private String contentType;
		@OneToMany(mappedBy="imgCouv")
		@JsonIgnore
		private Set<Manga> superMangas;

		public ImageCouv(long id,String fileName,String contentType,long fileSize,String storageId) {
			super();
			this.fileName = fileName;			
			this.fileSize = fileSize;	
			this.contentType=contentType;
			this.storageId = storageId;
			}

}
