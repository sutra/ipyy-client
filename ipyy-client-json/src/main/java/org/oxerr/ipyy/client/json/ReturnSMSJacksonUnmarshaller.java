package org.oxerr.ipyy.client.json;

import java.io.IOException;
import java.io.InputStream;

import org.oxerr.ipyy.client.core.ReturnSMSUnmarshaller;
import org.oxerr.ipyy.client.dto.ReturnSMS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

public class ReturnSMSJacksonUnmarshaller implements ReturnSMSUnmarshaller  {

	private final ObjectMapper objectMapper;

	public ReturnSMSJacksonUnmarshaller() {
		this.objectMapper = new ObjectMapper();
		this.objectMapper.registerModule(new JaxbAnnotationModule());
	}

	@Override
	public ReturnSMS unmarshal(InputStream is) {
		try {
			return this.objectMapper.readValue(is, ReturnSMS.class);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
