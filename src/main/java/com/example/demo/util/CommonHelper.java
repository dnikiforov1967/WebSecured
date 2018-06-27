/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.util;

import com.example.demo.jpa.model.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author dnikiforov
 */
public final class CommonHelper {

	private static final ObjectMapper mapper = new ObjectMapper();

	private CommonHelper() {

	}

	public static <T> T transformJsonToObject(InputStream inputStream, Class<T> clazz) throws IOException {
		return mapper.readValue(inputStream, clazz);
	}

	public static <T> T transformJsonToObject(String fileName, Class<T> clazz) throws IOException {
		final InputStream inputStream = Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream(fileName);
		return transformJsonToObject(inputStream, clazz);
	}

}
