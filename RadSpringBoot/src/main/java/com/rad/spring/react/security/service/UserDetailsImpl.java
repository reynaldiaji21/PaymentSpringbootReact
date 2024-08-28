package com.rad.spring.react.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rad.spring.react.entity.Users;

import lombok.Data;

@Data
public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = 1L;
	private String username;
	@JsonIgnore
	private String password;
	private String email;
	private String name;
	@JsonIgnore
	private String roles;
	
	public UserDetailsImpl(String username, String password, String email, String name, String roles) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.roles = roles;
	}
	
	
	public static  UserDetailsImpl build(Users user) {
		return new UserDetailsImpl(user.getId(), user.getPassword(), user.getEmail(), user.getName(), user.getRoles()) {

		};
		
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		if (StringUtils.hasText(roles)) {
			String[] splits = roles.replaceAll(" ", "").split(",");
			for (String value : splits) {
				auth.add(new SimpleGrantedAuthority(value));
			}
		}
		return auth;
	}

	

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	

}
