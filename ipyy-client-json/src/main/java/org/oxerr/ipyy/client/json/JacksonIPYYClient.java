package org.oxerr.ipyy.client.json;

import org.oxerr.ipyy.client.core.IPYYClient;

public class JacksonIPYYClient extends IPYYClient {

	public JacksonIPYYClient(String account, String password) {
		super("https://dx.ipyy.net/smsJson.aspx", new ReturnSMSJacksonUnmarshaller(), account, password);
	}

}
