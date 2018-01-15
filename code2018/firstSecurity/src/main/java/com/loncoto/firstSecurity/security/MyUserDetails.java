package com.loncoto.firstSecurity.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.loncoto.firstSecurity.metier.Utilisateur;

public class MyUserDetails implements UserDetails {

	private Utilisateur utilisateur;
	public MyUserDetails(Utilisateur utilisateur) {
		this.utilisateur= utilisateur;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		this.utilisateur.getRoles().stream().map(r-> r.getRoleName())//attention, suppose que les roles sont de formes ROLE_..
											.map(rolename-> new SimpleGrantedAuthority(rolename))
											.collect(Collectors.toList());
		return null;
	}
	
	@Override
	public String getPassword() {return this.utilisateur.getPassword();}
	@Override
	public String getUsername() {return this.utilisateur.getUserName();}
	@Override
	public boolean isAccountNonExpired() {return true;}
	@Override
	public boolean isAccountNonLocked() {return true;}
	@Override
	public boolean isCredentialsNonExpired() {return true;}
	@Override
	public boolean isEnabled() {return this.utilisateur.isEnabled();}

}
