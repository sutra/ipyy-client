package org.oxerr.ipyy.client.json;

import org.oxerr.ipyy.client.core.IPYYClient;

public class IPYYJsonClient extends IPYYClient {

	public IPYYJsonClient(String account, String password) {
		super("https://dx.ipyy.net/smsJson.aspx", new ReturnSMSJacksonUnmarshaller(), account, password);
	}

}
