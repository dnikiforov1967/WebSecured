/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.child.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 *
 * @author dnikiforov
 */
@Aspect
@Component
public class LoadGun {
	
	@Before(value = "@annotation(com.example.child.aop.annotations.Loadable)")
	public void load(JoinPoint point) throws Throwable {
		final Gun gun = (Gun)point.getTarget();
		gun.setLoads(6);
	}
	
}
