/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.child;

import com.example.child.aop.Gun;
import com.example.child.beans.LightningInterface;
import com.example.child.beans.MetallInterface;
import com.example.child.beans.ThunderInterface;
import com.example.child.beans.TicketInterface;
import com.example.common.InsectInterface;
import com.example.root.RootContext;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author dnikiforov
 */
@RunWith(SpringRunner.class)
@ContextHierarchy({
    @ContextConfiguration(name = "root", classes = RootContext.class)
    ,
        @ContextConfiguration(name = "child", classes = WebSecuredApplication.class)
})
@SpringBootTest
public class AopAndProxyTest implements Callable<UUID> {

    @Autowired
    @Qualifier("cooper")
    private MetallInterface metallInterface;

    @Autowired
    @Qualifier("mockitoParent")
    private InsectInterface insectInterfaceParent;

    @Autowired
    @Qualifier("mockito")
    private InsectInterface insectInterface;

    @Autowired
    private TicketInterface ticketInterface;

    @Autowired
    private LightningInterface lightningInterface;

    @Autowired
    private ThunderInterface thunderInterface;

    @Autowired
    @Qualifier("lightningTargetSource")
    private CommonsPool2TargetSource pool;

    @Autowired
    private Gun gun;

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
        System.out.println("Type of object: " + lightningInterface.getClass().getName());
    }

    @Test
    public void thunderTest() {
        final UUID idA = thunderInterface.boom();
        System.out.println("I have called Thunder::boom, reference is " + idA.toString());
        final UUID idB = thunderInterface.boom();
        System.out.println("I have called Thunder::boom, reference is " + idB.toString());
        assertNotEquals(idA, idB);
    }

    @Test
    public void gunTest() {
        int loads = gun.getLoads();
        assertEquals(0, loads);
        gun.shot();
        loads = gun.getLoads();
        assertEquals(5, loads);
    }

    @Override
    public UUID call() throws Exception {
        return lightningInterface.flush();
    }

    @Test
    public void testCooper() {
        metallInterface.name();
        assertEquals("Mockito 1", insectInterfaceParent.name());
        assertEquals("Mockito 2", insectInterface.name());
    }

}
