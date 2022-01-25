package com.liuhuachao.webservice.client;

import java.lang.reflect.Method;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

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

	/**
	 * 命名空间，如：http://server.webservice.liuhuachao.com/
	 */
	private String namespaceURI;

	/**
	 * 构造函数
	 * @param wsdlUrl
	 * @param namespaceURI
	 */
	public CxfDynamicClient(String wsdlUrl, String namespaceURI) {
		this.wsdlUrl = wsdlUrl;
		this.namespaceURI = namespaceURI;
	}

	/**
	 * 调用 WebService 方法
	 * @author liuhuachao
	 * @date 2022/1/25 14:04
	 * @param operationName 操作方法名称
	 * @param param 输入参数
	 * @return java.lang.Object[]
	 */
	public Object[] invoke(String operationName, Object param) throws Exception {
		JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
		Client client = clientFactory.createClient(wsdlUrl);
		// QName qName = new QName(namespaceURI, operationName);
		Object[] results = client.invoke(operationName, param);
		return results;
	}

	public Object[] invoke2(String operationName, Object param) throws Exception {
		JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
		Client client = clientFactory.createClient(wsdlUrl);

		Object paramObject = Thread.currentThread().getContextClassLoader().loadClass("com.liuhuachao.Person").newInstance();
		Method m1 = paramObject.getClass().getMethod("setId", Integer.class);
		Method m2 = paramObject.getClass().getMethod("setName", String.class);
		m1.invoke(paramObject, 100);
		m2.invoke(paramObject, "张三");

		Object[] results = client.invoke(operationName, paramObject);
		return results;
	}

	/**
	 * 调用 WebService 方法，设置超时时间
	 * @author liuhuachao
	 * @date 2022/1/25 14:06
	 * @param qName 操作方法名称
	 * @param param 输入参数
	 * @param timeOut 超时时间，单位毫秒
	 * @param allowChunking 是否允许分块
	 * @return java.lang.Object[]
	 */
	public Object[] invoke(String qName, Object param, long timeOut, boolean allowChunking) throws Exception {
		JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
		Client client = clientFactory.createClient(wsdlUrl);

		// 策略
		HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();

		// 连接超时(1分钟)
		httpClientPolicy.setConnectionTimeout(timeOut);

		// 响应超时(1分钟)
		httpClientPolicy.setReceiveTimeout(timeOut);

		// 不分块
		httpClientPolicy.setAllowChunking(allowChunking);

		HTTPConduit http = (HTTPConduit) client.getConduit();
		http.setClient(httpClientPolicy);

		QName qName1 = new QName(namespaceURI, qName);
		Object[] results = client.invoke(qName1, param);
		return results;
	}

}
