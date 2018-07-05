/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.beans.impl;

import com.example.demo.beans.ThunderInterface;
import java.util.UUID;

/**
 *
 * @author dnikiforov
 */
public class Thunder implements ThunderInterface {

	private final UUID id = UUID.randomUUID();

	@Override
	public UUID boom() {
		return id;
	}

}
