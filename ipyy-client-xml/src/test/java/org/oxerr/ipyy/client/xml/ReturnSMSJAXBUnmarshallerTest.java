package org.oxerr.ipyy.client.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBException;

import org.junit.jupiter.api.Test;
import org.oxerr.ipyy.client.dto.ReturnSMS;

class ReturnSMSJAXBUnmarshallerTest {

	@Test
	void testUnmarshalError() throws IOException, JAXBException {
		try (InputStream is = this.getClass().getResourceAsStream("error.xml")) {
			ReturnSMS ret = new ReturnSMSJAXBUnmarshaller().unmarshal(is);
			assertEquals("Faild", ret.getReturnStatus());
			assertEquals("需要签名", ret.getMessage());
			assertEquals(0, ret.getRemainPoint());
			assertEquals("", ret.getTaskId());
			assertEquals(0, ret.getSuccessCounts());
		}
	}

	@Test
	void testUnmarshalSuccess() throws IOException, JAXBException {
		try (InputStream is = this.getClass().getResourceAsStream("success.xml")) {
			ReturnSMS ret = new ReturnSMSJAXBUnmarshaller().unmarshal(is);
			assertEquals("Success", ret.getReturnStatus());
			assertEquals("操作成功", ret.getMessage());
			assertEquals(32731, ret.getRemainPoint());
			assertEquals("1900000000000000", ret.getTaskId());
			assertEquals(1, ret.getSuccessCounts());
		}
	}

}
