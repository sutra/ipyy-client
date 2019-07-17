package org.oxerr.ipyy.client.core;

import static org.apache.http.Consts.UTF_8;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.message.BasicNameValuePair;
import org.oxerr.ipyy.client.dto.ReturnSMS;

public class IPYYClient {

	private final String url;

	private final String account;
	private final String password;

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
		Response response = Request.Post(url).bodyForm(nvps, UTF_8).execute();
		try (InputStream is = response.returnContent().asStream()) {
			return this.returnSMSUnmarshaller.unmarshal(is);
		}
	}

}
