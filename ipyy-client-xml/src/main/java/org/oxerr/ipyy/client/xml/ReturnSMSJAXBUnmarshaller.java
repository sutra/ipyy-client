package org.oxerr.ipyy.client.xml;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.oxerr.ipyy.client.core.ReturnSMSUnmarshaller;
import org.oxerr.ipyy.client.dto.ReturnSMS;

public class ReturnSMSJAXBUnmarshaller implements ReturnSMSUnmarshaller {

	private final Unmarshaller unmarshaller;

	public ReturnSMSJAXBUnmarshaller() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ReturnSMS.class);
			this.unmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public ReturnSMS unmarshal(InputStream is) {
		try {
			return this.internalUnmarshal(is);
		} catch (JAXBException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private ReturnSMS internalUnmarshal(InputStream is) throws JAXBException {
		return (ReturnSMS) this.unmarshaller.unmarshal(is);
	}

}
