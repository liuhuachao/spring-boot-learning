package com.liuhuachao.webservice.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * ErpWebService 接口
 * @author liuhuachao
 * @date 2022/1/24
 */
@WebService(name="ErpWebService",targetNamespace = "http://liuhuachao.com")
public interface ErpWebService {
	/**
	 * 获取部门用户
	 * @author liuhuachao
	 * @date 2022/1/24 16:06
	 * @param deptNo
	 * @return java.lang.String
	 */
	@WebMethod(operationName = "getUsers")
	String getUsers(@WebParam(name="deptNo") String deptNo);
}
