/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.child.beans.impl;

import com.example.child.beans.TicketInterface;
import java.util.UUID;

/**
 *
 * @author dnikiforov
 */
public class Ticket implements TicketInterface {

	private final UUID id = UUID.randomUUID();

	@Override
	public UUID getId() {
		return id;
	}
	
}
