package org.oxerr.ipyy.client.core;

import java.io.IOException;
import java.io.InputStream;

import org.oxerr.ipyy.client.dto.ReturnSMS;

public interface ReturnSMSUnmarshaller {

	public ReturnSMS unmarshal(InputStream is) throws IOException;

}
