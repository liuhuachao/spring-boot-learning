package com.liuhuachao.webservice.server.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.liuhuachao.webservice.server.JwsWebService;

/**
 * JwsWebService 类
 * @author liuhuachao
 * @date 2022/1/24
 */
@WebService(name = "JwsWebService", targetNamespace = "http://server.webservice.liuhuachao.com/")
public class JwsWebServiceImpl implements JwsWebService {

	@WebMethod
	@Override
	public String getWeather(String cityName) {
		String weather = cityName + " Sunny 4℃";
		return weather;
	}

	@WebMethod
	@Override
	public String getEquipData(String equipCode) {
		return "设备数据：1,2,3";
	}

	@Override
	@WebMethod(exclude = true)
	public String listUsers(String deptNo) {
		return "部门用户：张三、李四";
	}

}
