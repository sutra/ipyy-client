package org.oxerr.ipyy.client.spring.boot.autoconfigure;

import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = IPYYClientProperties.PREFIX)
@Validated
public class IPYYClientProperties {

	static final String PREFIX = "org.oxerr.ipyy.client";

	private String account;
	private String password;

	private Integer socketTimeout;
	private Integer connectTimeout;

	private String proxy;

	@NotEmpty
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

	public Integer getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(Integer socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public Integer getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public String getProxy() {
		return proxy;
	}

	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

}
