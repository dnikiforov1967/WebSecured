/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.child.beans.impl;

import com.example.child.beans.LightningInterface;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dnikiforov
 */
public class Lightning implements LightningInterface {

	private final UUID id = UUID.randomUUID();
	
	@Override
	public UUID flush() {
		try {
			TimeUnit.MILLISECONDS.sleep(5000);
		} catch (InterruptedException ex) {
			Logger.getLogger(Lightning.class.getName()).log(Level.SEVERE, null, ex);
		}
		return id;
	}
	
}
