/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.jpa.api;

import com.example.demo.jpa.model.AppRole;
import com.example.demo.jpa.model.RoleEnum;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author dnikiforov
 */
@Transactional
public interface RoleJpaInterface extends JpaRepository<AppRole, RoleEnum> {
	
}
