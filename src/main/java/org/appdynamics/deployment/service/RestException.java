package org.appdynamics.deployment.service;

import org.apache.http.StatusLine;

public class RestException extends Exception {

	private static final long serialVersionUID = 1L;

	private int statusCode;
	private String statusMsg;

	public RestException(Exception e, int statusCode, String statusMsg) {
		super(e);
		this.statusCode=statusCode;
		this.statusMsg=statusMsg;
	}

	public RestException(String msg, int statusCode, String statusMsg) {
		super(msg);
		this.statusCode=statusCode;
		this.statusMsg=statusMsg;
	}
	
	public RestException(Exception e, StatusLine statusLine) {
		this(e, statusLine!=null ? statusLine.getStatusCode() : 0, statusLine!=null ? statusLine.getReasonPhrase() : null);
	}

	public RestException(String msg, StatusLine statusLine) {
		this(msg, statusLine!=null ? statusLine.getStatusCode() : 0, statusLine!=null ? statusLine.getReasonPhrase() : null);
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getStatusMsg() {
		return statusMsg;
	}
	
	public String getStatusLabel() {
		return "[Http Status Code : "+statusCode+" / Message : "+statusMsg+"]";
	}

}
