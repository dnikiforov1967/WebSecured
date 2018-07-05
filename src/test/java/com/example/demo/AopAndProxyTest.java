/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.beans.LightningInterface;
import com.example.demo.beans.TicketInterface;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.aop.target.CommonsPool2TargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author dnikiforov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AopAndProxyTest implements Callable<UUID> {

	@Autowired
	private TicketInterface ticketInterface;

	@Autowired
	private LightningInterface lightningInterface;

	@Autowired
	@Qualifier("lightningTargetSource")
	private CommonsPool2TargetSource pool;

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
	public void ticketTest() {
		final UUID idA = ticketInterface.getId();
		System.out.println("I have called Ticket::message, reference is " + idA.toString());
		final UUID idB = ticketInterface.getId();
		System.out.println("I have called Ticket::message, reference is " + idB.toString());
		assertNotEquals(idA, idB);
	}

	@Test
	public void lightningTest() throws InterruptedException, ExecutionException {
		final ExecutorService executor = Executors.newFixedThreadPool(3);
		final Future<UUID> submitA = executor.submit(this);
		final Future<UUID> submitB = executor.submit(this);
		final Future<UUID> submitC = executor.submit(this);
		final UUID idA = submitA.get();
		System.out.println("I have called Lightning::flush, reference is " + idA.toString());
		final UUID idB = submitB.get();;
		System.out.println("I have called Lightning::flush, reference is " + idB.toString());
		submitC.get();
		assertNotEquals(idA, idB);
		assertEquals(3, pool.getIdleCount());
		executor.shutdown();
	}

	@Override
	public UUID call() throws Exception {
		return lightningInterface.flush();
	}

}