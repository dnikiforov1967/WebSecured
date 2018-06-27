/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.jpa.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author dnikiforov
 */
@Entity
@Table(name = "appUserRole")
public class AppUserRole implements Serializable {

	@Id
	private AppUserRoleKey key;

	public AppUserRole() {
		this.key = new AppUserRoleKey();
	}

	public AppUserRole(AppUserRoleKey key) {
		this.key = key;
	}

	public AppUser getAppUser() {
		return key.getAppUser();
	}

	public void setAppUser(AppUser appUser) {
		key.setAppUser(appUser);
	}

	public AppRole getAppRole() {
		return key.getAppRole();
	}

	public void setAppRole(AppRole appRole) {
		key.setAppRole(appRole);
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 29 * hash + Objects.hashCode(this.key);
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
		if (!(obj instanceof AppUserRole)) {
			return false;
		}
		final AppUserRole other = (AppUserRole) obj;
		if (!Objects.equals(this.key, other.key)) {
			return false;
		}
		return true;
	}

}
