/**
 * 
 */
package com.mycloud.store.utils;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycloud.exception.BusinessException;

/**
 * @author Shawn
 *
 */
public class JacksonUtils {

	private final static ObjectMapper objectMapper = new ObjectMapper();

	private JacksonUtils() {
	}

	public static ObjectMapper getInstance() {
		return objectMapper;
	}

	public static <T> T json2Entity(String jsonStr, Class<T> clazz) {
		try {
			return objectMapper.readValue(jsonStr, clazz);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	public static String entity2Json(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> Map<String, Object> json2map(String jsonStr) {
		try {
			return objectMapper.readValue(jsonStr, Map.class);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	public static <T> T map2Entity(Map<?, ?> map, Class<T> clazz) {
		return objectMapper.convertValue(map, clazz);
	}

}
