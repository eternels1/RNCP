package com.loncoto.firstSecurity.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@NoArgsConstructor@ToString(exclude= {"utilisateurs"})
@Entity
public class Role {
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=100)
	private String roleName;
	@ManyToMany(mappedBy="roles")
	private Set<Utilisateur> utilisateurs;
	

	public Set<Utilisateur> getUtilisateurs(){
		if (utilisateurs==null) {
			utilisateurs=new HashSet<>();			
		}
		return utilisateurs;
	}
	
	public Role(int id, String roleName){
		super();
		this.id=id;
		this.roleName=roleName;
	}
	
	
	
}
