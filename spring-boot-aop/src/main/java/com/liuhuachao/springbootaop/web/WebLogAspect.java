package com.liuhuachao.springbootaop.web;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 统一Web日志处理切面
 * @author liuhuachao
 * @date 2021/11/26
 */
@Aspect
@Component
@Order(1)
public class WebLogAspect {

	//region 成员变量
	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

	/**
	 * 应用名称
	 */
	private String appName = "AOP";

	/**
	 * 接口名称
	 */
	private String interfaceName = "GET";

	/**
	 * 方法参数
	 */
	private String methodParameters = "";

	@Autowired
	private ObjectMapper objectMapper;
	//endregion

	/**
	 * 切点
	 */
	@Pointcut("execution(public * com.liuhuachao.springbootaop.web.*Controller.*(..))")
	public void webLog() {
	}

	/**
	 * 前置通知，执行前
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		//region 获取方法信息
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		Object parameter = getParameter(method, joinPoint.getArgs());
		methodParameters = objectMapper.writeValueAsString(parameter);
		Map<String, Object> parameterMap = objectMapper.readValue(methodParameters,new HashMap<String, Object>().getClass());
		//endregion

		appName = method.getDeclaringClass().getName();
		interfaceName = method.getName();

		String title = String.format("应用（%s）方法（%s）请求开始，%s", appName, interfaceName, System.lineSeparator());
		StringBuilder sb = new StringBuilder(title);
		sb.append("请求URL：");
		sb.append(request.getRequestURL().toString());
		sb.append(String.format(";%s请求参数：", System.lineSeparator()));
		sb.append(methodParameters);
		logger.info(sb.toString());
	}

	/**
	 * 后置通知，执行后
	 * @param ret
	 * @throws Throwable
	 */
	@AfterReturning(value = "webLog()", returning = "ret")
	public void doAfterReturning(Object ret) throws Throwable {
	}

	/**
	 * 环绕通知，执行前后
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("webLog()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		// 开始时间
		long startTime = System.currentTimeMillis();

		// 获取当前请求对象
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// 请求结果
		Object result = joinPoint.proceed();

		// 结束时间
		long endTime = System.currentTimeMillis();

		// 消耗时间
		int spendTime = (int) (endTime - startTime);

		String title = String.format("应用（%s）方法（%s）请求结束，%s", appName, interfaceName, System.lineSeparator());
		StringBuilder sb = new StringBuilder(title);
		String timeStr = String.format("开始时间：%s,结束时间：%s，消耗时间：%s", startTime, endTime, spendTime);
		sb.append(timeStr);
		sb.append(String.format(";%s请求URL：", System.lineSeparator()));
		sb.append(request.getRequestURL().toString());
		sb.append(String.format(";%s返回参数：", System.lineSeparator()));
		sb.append(objectMapper.writeValueAsString(result));

		logger.info(sb.toString());

		return result;
	}

	/**
	 * 根据方法和传入的参数获取请求参数
	 */
	private Object getParameter(Method method, Object[] args) {
		List<Object> argList = new ArrayList<>();
		Parameter[] parameters = method.getParameters();
		for (int i = 0; i < parameters.length; i++) {
			//将RequestBody注解修饰的参数作为请求参数
			RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
			if (requestBody != null) {
				argList.add(args[i]);
			}
			//将RequestParam注解修饰的参数作为请求参数
			RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
			if (requestParam != null) {
				Map<String, Object> map = new HashMap<>();
				String key = parameters[i].getName();
				if (!StringUtils.isEmpty(requestParam.value())) {
					key = requestParam.value();
				}
				map.put(key, args[i]);
				argList.add(map);
			}
		}
		if (argList.size() == 0) {
			return null;
		}
		else if (argList.size() == 1) {
			return argList.get(0);
		}
		else {
			return argList;
		}
	}

}
