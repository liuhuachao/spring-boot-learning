package com.liuhuachao.webservice.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * WebService 客户端，使用 CXF JaxWsDynamicClientFactory 实现
 * @author liuhuachao
 * @date 2022/1/24
 */
public class CxfDynamicClient {

	/**
	 * wsdl 地址，如：http://localhost:8080/webservice/erp?wsdl
	 */
	private String wsdlUrl;

	public CxfDynamicClient(String wsdlUrl) {
		this.wsdlUrl = wsdlUrl;
	}

	/**
	 * 调用 WebService 方法
	 * @param qName WebMethod 方法名
	 * @param param 输入参数
	 * @return java.lang.Object
	 * @author liuhuachao
	 * @date 2022/1/24 17:22
	 */
	public Object invokeWebService(String qName, Object param) throws Exception {
		JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
		Client client = clientFactory.createClient(wsdlUrl);
		Object[] results = client.invoke(qName, param);
		return results[0];
	}

}
