/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.child.service.impl;

import com.example.child.jpa.api.RoleJpaInterface;
import com.example.child.jpa.model.AppRole;
import com.example.child.service.RoleServiceInterface;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author dnikiforov
 */
@Component("jpaRoleService")
@Transactional
public class RoleService implements RoleServiceInterface {

	@Autowired
	private RoleJpaInterface roleJpa;	
	
	@Override
	public AppRole create(AppRole appRole) {
		return roleJpa.saveAndFlush(appRole);
	}
	
}
