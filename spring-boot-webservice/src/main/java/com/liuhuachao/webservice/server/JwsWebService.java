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
	 * @author liuhuachao
	 * @date 2022/1/24 13:33
	 * @param cityName 城市名
	 * @return java.lang.String
	 */
	@WebMethod(operationName = "getWeather")
	String getWeather(@WebParam(name="cityName") String cityName);

	/**
	 * 获取设备数据
	 * @author liuhuachao
	 * @date 2022/1/24 15:23
	 * @param equipCode
	 * @return java.lang.String
	 */
	String getEquipData(String equipCode);

	/**
	 * 获取部门用户
	 * @author liuhuachao
	 * @date 2022/1/24 15:24
	 * @param deptNo
	 * @return java.lang.String
	 */
	String listUsers(String deptNo);
}
