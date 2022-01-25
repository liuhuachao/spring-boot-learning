package com.liuhuachao.webservice.client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

/**
 * Axis Call 方式调用 WebService
 * @author liuhuachao
 * @date 2022/1/25
 */
public class AxisCall {

	/**
	 * wsdl 地址，如：http://localhost:8080/webservice/erp?wsdl
	 */
	private String wsdlUrl;

	/**
	 * 命名空间，如：http://server.webservice.liuhuachao.com/
	 */
	private String namespaceURI;

	public AxisCall(String wsdlUrl, String namespaceURI){
		this.wsdlUrl = wsdlUrl;
		this.namespaceURI = namespaceURI;
	}

	/**
	 * 构造函数
	 * @param localPart
	 * @param params
	 * @return
	 */
	public Object invoke(String localPart, String[] params) {
		Object result = null;

		Service service = new Service();

		try {
			Call call = (Call) service.createCall();

			/**
			 * 设置 wsdl 的地址
			 */
			call.setTargetEndpointAddress(new URL(wsdlUrl));

			// 设置编码方式
			call.setEncodingStyle("utf-8");

			// 设置 SOAPAction
			call.setUseSOAPAction(true);
			call.setSOAPActionURI(wsdlUrl + localPart);

			/**
			 * 设置操作方法
			 */
			QName qName = new QName(namespaceURI, localPart);
			call.setOperationName(qName);

			/**
			 * 设置输入参数
			 */
			call.addParameter(new QName(namespaceURI,params[0]), XMLType.XSD_STRING, ParameterMode.IN);

			/**
			 * 设置返回类型
			 */
			call.setReturnType(XMLType.XSD_STRING);

			result = call.invoke(new Object[]{params[1]});

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
