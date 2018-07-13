/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.child.aop;

import com.example.child.aop.annotations.Loadable;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Component;

/**
 *
 * @author dnikiforov
 */
@Component
public class Gun {

	private AtomicInteger loads = new AtomicInteger();

	public int getLoads() {
		return loads.get();
	}

	public void setLoads(int cartr) {
		loads.set(cartr);
	}

	
	@Loadable
	public void shot() {
		if (loads.get() > 0) {
			loads.decrementAndGet();
		} else {
			throw new RuntimeException("Malshot");
		}
	}
}
