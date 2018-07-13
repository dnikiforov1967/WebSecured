/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.child.aop;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

/**
 *
 * @author dnikiforov
 */
@Component
public class SimpleAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] os, Object o) throws Throwable {
		System.out.println("Before Advice was called");
	}
	
}
