package com.liuhuachao.springbootaop.service.proxy;

import com.liuhuachao.springbootaop.service.CacheService;

/**
 * 代理设计模式实现静态 AOP
 * @author liuhuachao
 * @date 2021/12/27
 */
public class CacheServiceProxy implements CacheService {

	private CacheService target;

	public CacheServiceProxy(CacheService cacheService){
		this.target = cacheService;
	}

	@Override
	public void m1() {

		System.out.println("使用代理设计模式方法调用前");

		this.target.m1();

		System.out.println("使用代理设计模式方法调用后");
	}

	@Override
	public void m2() {

		this.target.m2();

	}

}
