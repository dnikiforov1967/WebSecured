/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.beans.TicketInterface;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author dnikiforov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AopAndProxyTest {
	
	@Autowired
	private TicketInterface ticketInterface;
	
	public AopAndProxyTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//
	@Test
	public void hello() {
		final UUID idA = ticketInterface.getId();
		System.out.println("I have called Ticket::message, reference is "+idA.toString());
		final UUID idB = ticketInterface.getId();
		System.out.println("I have called Ticket::message, reference is "+idB.toString());
		assertNotEquals(idA, idB);
	}
}
