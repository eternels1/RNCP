package com.khalid.blogSecu.metier;

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

@Entity
@Getter@Setter@NoArgsConstructor@ToString(exclude= {"users"})
public class Role {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id								private int id;
	@Column(length=100)				private String roleName;
	@ManyToMany(mappedBy="roles")	private Set<User> users;

	public Set<User>getUsers(){
		if (users==null) {
			users= new HashSet<>();
		}
		return users;
	}

	public Role(int id, String roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
	}
	
	
}
