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
	 * Method: invoke(String qName, Object param)
	 */
	@Test
	public void testInvoke() throws Exception {
		String wsdlUrl = "http://localhost:8080/webservice/jws?wsdl";
		String namespaceURI = "http://server.webservice.liuhuachao.com/";
		CxfDynamicClient client = new CxfDynamicClient(wsdlUrl, namespaceURI);
		Object[] results = client.invoke("getWeather", "WuHan");
		Assert.assertEquals(results[0].toString(), "WuHan Sunny 4℃");
	}

} 
