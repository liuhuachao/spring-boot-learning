package com.liuhuachao.webservice.server.impl;

import com.liuhuachao.webservice.server.ErpWebService;

/**
 * ErpWebServiceImpl 实现类
 * @author liuhuachao
 * @date 2022/1/24
 */
public class ErpWebServiceImpl implements ErpWebService {
	@Override
	public String getUsers(String deptNo) {
		return String.format("部门:%s 的用户：张三、李四",deptNo);
	}
}
