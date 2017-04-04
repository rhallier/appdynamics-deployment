package org.appdynamics.deployment.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mashape.unirest.http.ObjectMapper;

public class JacksonObjectMapper implements ObjectMapper {

	private final com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper;
	
	public JacksonObjectMapper() {
		jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
		jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		jacksonObjectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
	}

	@SuppressWarnings("unchecked")
	public <T> T readValue(String value, Class<T> valueType) {
		try {
			if(valueType.isArray()) {
				final Class<T> ofArray = (Class<T>) valueType.getComponentType();
				ArrayType at = TypeFactory.defaultInstance().constructArrayType(ofArray);
				return jacksonObjectMapper.readValue(value, at);
			}
			return jacksonObjectMapper.readValue(value, valueType);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String writeValue(Object value) {
		try {
			return jacksonObjectMapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
