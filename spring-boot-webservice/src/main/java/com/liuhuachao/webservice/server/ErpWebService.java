package com.liuhuachao.webservice.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * ErpWebService 接口
 * @author liuhuachao
 * @date 2022/1/24
 */
@WebService(name = "ErpWebService", targetNamespace = "http://liuhuachao.com")
public interface ErpWebService {
	/**
	 * 获取部门用户
	 * @param deptNo
	 * @return java.lang.String
	 * @author liuhuachao
	 * @date 2022/1/24 16:06
	 */
	@WebMethod(operationName = "getUsers")
	String getUsers(@WebParam(name = "deptNo") String deptNo);
}
