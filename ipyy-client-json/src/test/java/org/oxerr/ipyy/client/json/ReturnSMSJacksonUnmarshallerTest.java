package org.oxerr.ipyy.client.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.oxerr.ipyy.client.dto.ReturnSMS;

class ReturnSMSJacksonUnmarshallerTest {

	@Test
	void testUnmarshal() throws IOException {
		try (InputStream is = this.getClass().getResourceAsStream("error.json")) {
			ReturnSMS ret = new ReturnSMSJacksonUnmarshaller().unmarshal(is);
			assertEquals("Faild", ret.getReturnStatus());
			assertEquals("需要签名", ret.getMessage());
			assertEquals(0, ret.getRemainPoint().longValue());
			assertEquals(0, ret.getSuccessCounts().intValue());
		}
	}

}
