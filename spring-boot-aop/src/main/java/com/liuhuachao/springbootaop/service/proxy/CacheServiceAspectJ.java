package com.liuhuachao.springbootaop.service.proxy;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 通过 AspectJ 实现静态代理 AOP
 * @author liuhuachao
 * @date 2021/12/27
 */
@Aspect
@Component
public class CacheServiceAspectJ {

	@Pointcut("execution(* com.liuhuachao.springbootaop.service.impl.CacheServiceImpl.*())")
	public void webLog(){
	}

	/**
	 * 前置通知，执行前
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		System.out.println("方法执行前通知");
	}

	/**
	 * 后置通知，执行后
	 * @param ret
	 * @throws Throwable
	 */
	@AfterReturning(value = "webLog()", returning = "ret")
	public void doAfterReturning(Object ret) throws Throwable {
		System.out.println("方法执行后通知");
	}

	/**
	 * 环绕通知，执行前后
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("webLog()")
	public void doAround(JoinPoint joinPoint) throws Throwable {
		System.out.println("方法执行前后环绕通知");
	}

	/**
	 * 抛出异常时通知
	 * @param point
	 * @param ex
	 */
	public void doThrowing(JoinPoint point, Throwable ex) {
		System.out.println(String.format("类(%s)的方法(%s)抛出异常通知：%s",
				point.getTarget().getClass().getName(),
				point.getSignature().getName(),
				ex.getMessage()
				));
	}

}
