/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.jms.model;

import com.example.demo.web.resource.AppUserResource;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author dnikiforov
 */
public class JmsResult implements Serializable {

	private AppUserResource resource;
	private Exception exception;	
	
	public JmsResult(AppUserResource resource, Exception exception) {
		this.resource = resource;
		this.exception = exception;
	}

	public AppUserResource getResource() {
		return resource;
	}

	public Exception getException() {
		return exception;
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.resource);
		hash = 97 * hash + Objects.hashCode(this.exception);
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
		final JmsResult other = (JmsResult) obj;
		if (!Objects.equals(this.resource, other.resource)) {
			return false;
		}
		if (!Objects.equals(this.exception, other.exception)) {
			return false;
		}
		return true;
	}

	
}
