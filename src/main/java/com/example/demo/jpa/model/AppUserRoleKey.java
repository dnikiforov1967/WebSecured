/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.jpa.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author dnikiforov
 */
@Embeddable
public class AppUserRoleKey implements Serializable {

	@ManyToOne
	@JoinColumn(name = "appUser")
	private AppUser appUser;
	
	private AppRole appRole;

	public AppUserRoleKey() {
	}

	public AppUserRoleKey(AppUser appUser, AppRole appRole) {
		this.appUser = appUser;
		this.appRole = appRole;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public AppRole getAppRole() {
		return appRole;
	}

	public void setAppRole(AppRole appRole) {
		this.appRole = appRole;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 47 * hash + Objects.hashCode(this.appUser);
		hash = 47 * hash + Objects.hashCode(this.appRole);
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
		final AppUserRoleKey other = (AppUserRoleKey) obj;
		if (!Objects.equals(this.appUser, other.appUser)) {
			return false;
		}
		if (this.appRole != other.appRole) {
			return false;
		}
		return true;
	}

}
