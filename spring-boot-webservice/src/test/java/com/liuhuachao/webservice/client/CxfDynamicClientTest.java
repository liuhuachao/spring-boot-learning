package com.liuhuachao.webservice.client;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

/**
 * CxfDynamicClient Tester.
 * @author <liuhuachao>
 * @version 1.0
 * @since <pre>01/24/2022</pre>
 */
public class CxfDynamicClientTest {

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: invokeWebService(String wsdlUrl, String qName, Object param)
	 */
	@Test
	public void testInvokeWebService() throws Exception {
		String wsdlUrl = "http://localhost:8080/webservice/jws?wsdl";
		CxfDynamicClient client = new CxfDynamicClient(wsdlUrl);
		Object result = client.invokeWebService("getWeather", "WuHan");
		Assert.assertEquals(result.toString(), "WuHan Sunny 4℃");
	}

} 
