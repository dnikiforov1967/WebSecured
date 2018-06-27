/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.web;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 *
 * @author dnikiforov
 */
@RestController
@RequestMapping("/welcome")
public class WelcomeRestController {

	@RequestMapping(value = "/{id}", method = GET)
	public String get(@PathVariable String id) {
		return id;
	}

}
