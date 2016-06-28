package org.appdynamics.deployment.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.appdynamics.deployment.model.Timerange;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mashape.unirest.http.HttpMethod;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.body.Body;

public class RestHttpRequest  {
	
	private static com.fasterxml.jackson.databind.ObjectMapper xmlMapper = new XmlMapper();

	public final static ObjectMapper jsonMapper = new ObjectMapper() {
		private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

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
	};

	private HttpRequest request;
	
	public RestHttpRequest(HttpRequest request) {
		this.request=request;
	}

	// Appdynamics methods
	public RestHttpRequest toJson() {
		request.queryString("output","JSON");
		return this;
	}
	
	public RestHttpRequest between(Timerange timerange) {
		request.queryString("time-range-type","BETWEEN_TIMES")
				.queryString("start-time", timerange.getStartMs())
				.queryString("end-time", timerange.getEndMs());
		return this;
	}
	
	public <T> T asXml(Class<T> clazz) throws UnirestException {
		HttpResponse<String> arep = request.asString();
		String a = arep.getBody();

		if(arep.getStatus()!=200)
			throw new UnirestException(a);
		
		T result=null;
		try {
			result = xmlMapper.readValue(a, clazz);
		} catch (JsonParseException e) {
			throw new UnirestException(e);
		} catch (JsonMappingException e) {
			throw new UnirestException(e);
		} catch (IOException e) {
			throw new UnirestException(e);
		}
		
		return result;
	}


	// Delegate methods
	public int hashCode() {
		return request.hashCode();
	}

	public HttpRequest getHttpRequest() {
		return request.getHttpRequest();
	}

	public HttpResponse<String> asString() throws UnirestException {
		return request.asString();
	}

	public Future<HttpResponse<String>> asStringAsync() {
		return request.asStringAsync();
	}

	public RestHttpRequest routeParam(String name, String value) {
		request.routeParam(name, value);
		return this;
	}

	public Future<HttpResponse<String>> asStringAsync(Callback<String> callback) {
		return request.asStringAsync(callback);
	}

	public HttpResponse<JsonNode> asJson() throws UnirestException {
		return request.asJson();
	}

	public Future<HttpResponse<JsonNode>> asJsonAsync() {
		return request.asJsonAsync();
	}

	public RestHttpRequest basicAuth(String username, String password) {
		request.basicAuth(username, password);
		return this;
	}

	public Future<HttpResponse<JsonNode>> asJsonAsync(Callback<JsonNode> callback) {
		return request.asJsonAsync(callback);
	}

	public RestHttpRequest header(String name, String value) {
		request.header(name, value);
		return this;
	}

	public <T> HttpResponse<T> asObject(Class<? extends T> responseClass) throws UnirestException {
		return request.asObject(responseClass);
	}

	public <T> Future<HttpResponse<T>> asObjectAsync(Class<? extends T> responseClass) {
		return request.asObjectAsync(responseClass);
	}

	public RestHttpRequest headers(Map<String, String> headers) {
		request.headers(headers);
		return this;
	}

	public <T> Future<HttpResponse<T>> asObjectAsync(Class<? extends T> responseClass, Callback<T> callback) {
		return request.asObjectAsync(responseClass, callback);
	}

	public RestHttpRequest queryString(String name, Collection<?> value) {
		request.queryString(name, value);
		return this;
	}

	public HttpResponse<InputStream> asBinary() throws UnirestException {
		return request.asBinary();
	}

	public RestHttpRequest queryString(String name, Object value) {
		request.queryString(name, value);
		return this;
	}

	public Future<HttpResponse<InputStream>> asBinaryAsync() {
		return request.asBinaryAsync();
	}

	public Future<HttpResponse<InputStream>> asBinaryAsync(Callback<InputStream> callback) {
		return request.asBinaryAsync(callback);
	}

	public boolean equals(Object obj) {
		return request.equals(obj);
	}

	public RestHttpRequest queryString(Map<String, Object> parameters) {
		request.queryString(parameters);
		return this;
	}

	public HttpMethod getHttpMethod() {
		return request.getHttpMethod();
	}

	public String getUrl() {
		return request.getUrl();
	}

	public Map<String, List<String>> getHeaders() {
		return request.getHeaders();
	}

	public Body getBody() {
		return request.getBody();
	}

	public String toString() {
		return request.toString();
	}

}
