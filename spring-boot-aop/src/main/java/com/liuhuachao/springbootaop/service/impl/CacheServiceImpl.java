package com.liuhuachao.springbootaop.service.impl;

import com.liuhuachao.springbootaop.service.CacheService;

/**
 * IService 接口的实现类
 * @author liuhuachao
 * @date 2021/12/27
 */
public class CacheServiceImpl implements CacheService {

	@Override
	public void m1() {
		System.out.println("我是 CacheServiceImpl 中的 m1 方法");
	}

	@Override
	public void m2() {
		System.out.println("我是 CacheServiceImpl 中的 m2 方法");
	}

}
