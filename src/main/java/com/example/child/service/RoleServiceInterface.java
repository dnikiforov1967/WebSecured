/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.child.service;

import com.example.child.jpa.model.AppRole;

/**
 *
 * @author dnikiforov
 */
public interface RoleServiceInterface {

	AppRole create(AppRole appRole);
}
