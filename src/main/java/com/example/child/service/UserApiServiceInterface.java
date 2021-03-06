/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.child.service;

import com.example.child.jpa.model.AppUser;
import java.util.List;

/**
 *
 * @author dnikiforov
 */
public interface UserApiServiceInterface {

	AppUser create(AppUser user);
	
	AppUser update(AppUser user);	

	void delete(AppUser user);

	AppUser find(AppUser user);

	AppUser findByName(String userName);

	List<AppUser> getAllUsers();

}
