/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.jpa.model.AppUser;
import java.util.List;

/**
 *
 * @author dnikiforov
 */
public interface UserApiServiceInterface {

	AppUser create(AppUser user);

	void delete(AppUser user);

	AppUser find(AppUser user);

	AppUser findByName(String userName);

	List<AppUser> getAllUsers();

}
