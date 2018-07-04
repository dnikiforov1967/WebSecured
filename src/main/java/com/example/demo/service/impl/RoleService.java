/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service.impl;

import com.example.demo.jpa.api.RoleJpaInterface;
import com.example.demo.jpa.model.AppRole;
import com.example.demo.service.RoleServiceInterface;
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
