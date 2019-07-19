package org.oxerr.ipyy.client.core;

import static org.apache.http.Consts.UTF_8;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.message.BasicNameValuePair;
import org.oxerr.ipyy.client.dto.ReturnSMS;

public class IPYYClient {

	private final String url;

	private final String account;
	private final String password;

	private Integer socketTimeout;
	private Integer connectTimeout;
	private HttpHost proxy;

	private final ReturnSMSUnmarshaller returnSMSUnmarshaller;

	public IPYYClient(
		String url,
		ReturnSMSUnmarshaller returnSMSUnmarshaller,
		String account,
		String password
	) {
		this.url = url;
		this.returnSMSUnmarshaller = returnSMSUnmarshaller;
		this.account = account;
		this.password = password;
	}

	/**
	 * Defines the socket timeout ({@code SO_TIMEOUT}) in milliseconds,
	 * which is the timeout for waiting for data or, put differently,
	 * a maximum period inactivity between two consecutive data packets.
	 * <p>
	 * A timeout value of zero is interpreted as an infinite timeout.
	 * A negative value is interpreted as undefined (system default).
	 * </p>
	 * <p>
	 * Default: {@code -1}
	 * </p>
	 * @param socketTimeout the timeout for waiting for data or,
	 * put differently, a maximum period inactivity between two consecutive
	 * data packets.
	 * @see RequestConfig#getSocketTimeout()
	 */
	public void setSocketTimeout(Integer socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	/**
	 * Determines the timeout in milliseconds until a connection is established.
	 * A timeout value of zero is interpreted as an infinite timeout.
	 * <p>
	 * A timeout value of zero is interpreted as an infinite timeout.
	 * A negative value is interpreted as undefined (system default).
	 * </p>
	 * <p>
	 * Default: {@code -1}
	 * </p>
	 * @param connectTimeout the timeout in milliseconds until a connection is established
	 * @see RequestConfig#getConnectTimeout()
	 */
	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	/**
	 * The HTTP proxy to be used for request execution.
	 * <p>
	 * Default: {@code null}
	 * </p>
	 * @param proxy the HTTP proxy
	 * @see RequestConfig#getProxy()
	 */
	public void setProxy(HttpHost proxy) {
		this.proxy = proxy;
	}

	public ReturnSMS send(String mobile, String content) throws IOException {
		return send(new String[] { mobile }, content);
	}

	public ReturnSMS send(String[] mobiles, String content) throws IOException {
		List<NameValuePair> nvps = Arrays.asList(
			new BasicNameValuePair("action", "send"),
			new BasicNameValuePair("userid", ""),
			new BasicNameValuePair("account", this.account),
			new BasicNameValuePair("password", this.password),
			new BasicNameValuePair("mobile", String.join(",", mobiles)),
			new BasicNameValuePair("content", content),
			new BasicNameValuePair("sendTime", ""),
			new BasicNameValuePair("extno", "")
		);
		Request request = Request.Post(url);
		if (this.socketTimeout != null) {
			request.socketTimeout(this.socketTimeout);
		}
		if (this.connectTimeout != null) {
			request.connectTimeout(this.connectTimeout);
		}
		if (this.proxy != null) {
			request.viaProxy(this.proxy);
		}
		Response response = request.bodyForm(nvps, UTF_8).execute();
		try (InputStream is = response.returnContent().asStream()) {
			return this.returnSMSUnmarshaller.unmarshal(is);
		}
	}

}
