package com.liuhuachao.webservice.client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * AxisCall Tester.
 * @author <liuhuachao>
 * @version 1.0
 * @since <pre>01/25/2022</pre>
 */
public class AxisCallTest {

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: invoke(String localPart, String[] params)
	 */
	@Test
	public void testInvoke() throws Exception {
		String wsdlUrl = "http://localhost:8080/webservice/jws?wsdl";
		String namespaceURI = "http://server.webservice.liuhuachao.com/";
		String methodName = "getWeather";
		// 输入参数的名字和值
		String[] params = {"cityName", "WuHan"};

		AxisCall axisCall = new AxisCall(wsdlUrl, namespaceURI);
		axisCall.invoke(methodName, params);
	}

} 
