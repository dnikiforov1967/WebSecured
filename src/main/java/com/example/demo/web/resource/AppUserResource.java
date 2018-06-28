/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.web.resource;

import com.example.demo.jpa.model.AppRole;
import com.example.demo.jpa.model.AppUser;
import com.example.demo.jpa.model.AppUserRole;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 *
 * @author dnikiforov
 */
public class AppUserResource implements Comparable<AppUserResource>, Serializable {

	private String username;
	private String password;
	private Set<AppRole> roles = new HashSet<>();

	public AppUserResource() {
	}

	public AppUserResource(AppUser appUser) {
		this.username = appUser.getUsername();
		this.password = appUser.getPassword();
		final Set<AppUserRole> appUserRoles = appUser.getAppUserRoles();
		if (appUserRoles != null) {
			this.roles = appUserRoles
					.stream()
					.map(AppUserRole::getAppRole)
					.collect(Collectors.toSet());
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<AppRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<AppRole> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.username);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final AppUserResource other = (AppUserResource) obj;
		if (!Objects.equals(this.username, other.username)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(AppUserResource t) {
		return this.username.compareTo(t.username);
	}

}
