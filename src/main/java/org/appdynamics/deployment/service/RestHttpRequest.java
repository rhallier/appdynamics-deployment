package org.appdynamics.deployment.service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.appdynamics.deployment.model.Timerange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.HttpMethod;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.body.Body;

public class RestHttpRequest  {
	
	private final Logger logger = LoggerFactory.getLogger(RestHttpRequest.class);

	public final static com.fasterxml.jackson.databind.ObjectMapper xmlMapper = new XmlObjectMapper();

	public final static ObjectMapper jsonMapper = new JacksonObjectMapper();

	private HttpRequest request;
	
	public RestHttpRequest(HttpRequest request) {
		this.request=request;
	}

	// Accessors
	public HttpRequest getHttpRequest() {
		return request.getHttpRequest();
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

	// Build request
	public RestHttpRequest routeParam(String name, String value) {
		request.routeParam(name, value);
		return this;
	}

	public RestHttpRequest between(Timerange timerange) {
		request.queryString("time-range-type","BETWEEN_TIMES")
				.queryString("start-time", timerange.getStartMs())
				.queryString("end-time", timerange.getEndMs());
		return this;
	}
	
	public RestHttpRequest beforeNow(Timerange timerange) {
		request.queryString("time-range-type","BEFORE_NOW")
				.queryString("duration-in-mins", timerange.getDurationInMins());
		return this;
	}
	
	public RestHttpRequest basicAuth(String username, String password) {
		request.basicAuth(username, password);
		return this;
	}

	public RestHttpRequest header(String name, String value) {
		request.header(name, value);
		return this;
	}

	public RestHttpRequest headers(Map<String, String> headers) {
		request.headers(headers);
		return this;
	}

	public RestHttpRequest queryString(String name, Collection<?> value) {
		request.queryString(name, value);
		return this;
	}

	public RestHttpRequest queryString(String name, Object value) {
		request.queryString(name, value);
		return this;
	}

	public RestHttpRequest queryString(Map<String, Object> parameters) {
		request.queryString(parameters);
		return this;
	}

	public RestHttpRequest toJson() {
		request.queryString("output","JSON");
		return this;
	}
	
	// Execute Request
	public <T> T asXml(Class<T> clazz) throws RestException {
		
		HttpResponse<String> arep = null;
		String body=null;
		T result;
		
		try {
			arep = RestClientHelper.request(request, String.class);
			body = arep.getBody();
	
			result = xmlMapper.readValue(body, clazz);
			
		} catch (JsonParseException e) {
			logger.error("Http Status : "+arep.getStatus()+" "+arep.getStatusText()+", Body="+body);
			throw new RestException(e, arep.getStatus(), arep.getStatusText());
		} catch (JsonMappingException e) {
			logger.error("Http Status : "+arep.getStatus()+" "+arep.getStatusText()+", Body="+body);
			throw new RestException(e, arep.getStatus(), arep.getStatusText());
		} catch (IOException e) {
			logger.error("Http Status : "+arep.getStatus()+" "+arep.getStatusText()+", Body="+body);
			throw new RestException(e, arep.getStatus(), arep.getStatusText());
		}
		
		return result;
	}

	public HttpResponse<String> asString() throws RestException {
		return RestClientHelper.request(request,String.class);
	}

	public Future<HttpResponse<String>> asStringAsync() {
		return RestClientHelper.requestAsync(request, String.class, null);
	}

	public Future<HttpResponse<String>> asStringAsync(Callback<String> callback) {
		return RestClientHelper.requestAsync(request, String.class, callback);
	}

	public HttpResponse<JsonNode> asJson() throws RestException {
		return RestClientHelper.request(request, JsonNode.class);
	}

	public Future<HttpResponse<JsonNode>> asJsonAsync() {
		return RestClientHelper.requestAsync(request, JsonNode.class, null);
	}

	public Future<HttpResponse<JsonNode>> asJsonAsync(Callback<JsonNode> callback) {
		return RestClientHelper.requestAsync(request, JsonNode.class, callback);
	}

	public <T> HttpResponse<T> asObject(Class<T> responseClass) throws RestException {
		return RestClientHelper.request(request, responseClass);
	}

	public <T> Future<HttpResponse<T>> asObjectAsync(Class<T> responseClass) {
		return RestClientHelper.requestAsync(request, responseClass, null);
	}

	public <T> Future<HttpResponse<T>> asObjectAsync(Class<T> responseClass, Callback<T> callback) {
		return RestClientHelper.requestAsync(request, responseClass, callback);
	}

	// System methods
	public int hashCode() {
		return request.hashCode();
	}

	public boolean equals(Object obj) {
		return request.equals(obj);
	}

	public String toString() {
		return request.toString();
	}
}
