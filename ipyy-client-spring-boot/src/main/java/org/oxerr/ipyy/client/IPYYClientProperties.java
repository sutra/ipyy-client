package org.oxerr.ipyy.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = IPYYClientProperties.PREFIX)
public class IPYYClientProperties {

	static final String PREFIX = "org.oxerr.ipyy.client";

	private String account;
	private String password;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
