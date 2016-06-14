package org.appdynamics.deployment.model;

public class Controller {
	private String url;
	private String account;
	private String username;
	private String password;

	public Controller(String url, String account, String username, String password) {
		super();
		this.url = url;
		this.account = account;
		this.username = username;
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
