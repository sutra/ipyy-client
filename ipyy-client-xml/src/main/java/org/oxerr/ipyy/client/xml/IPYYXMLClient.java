package org.oxerr.ipyy.client.xml;

import org.oxerr.ipyy.client.core.IPYYClient;

public class IPYYXMLClient extends IPYYClient {

	public IPYYXMLClient(String account, String password) {
		super("https://dx.ipyy.net/sms.aspx", new ReturnSMSJAXBUnmarshaller(), account, password);
	}

}
