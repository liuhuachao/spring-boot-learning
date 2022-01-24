package com.liuhuachao.webservice.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * JwsWebService 接口
 * @author liuhuachao
 * @date 2022/1/24
 */
@WebService
public interface JwsWebService {

	/**
	 * 获取天气，通过城市名
	 * @param cityName 城市名
	 * @return java.lang.String
	 * @author liuhuachao
	 * @date 2022/1/24 13:33
	 */
	@WebMethod(operationName = "getWeather")
	String getWeather(@WebParam(name = "cityName") String cityName);

	/**
	 * 获取设备数据
	 * @param equipCode
	 * @return java.lang.String
	 * @author liuhuachao
	 * @date 2022/1/24 15:23
	 */
	String getEquipData(String equipCode);

	/**
	 * 获取部门用户
	 * @param deptNo
	 * @return java.lang.String
	 * @author liuhuachao
	 * @date 2022/1/24 15:24
	 */
	String listUsers(String deptNo);
}
