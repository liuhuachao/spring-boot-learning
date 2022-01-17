package com.liuhuachao.springbootjwt.config;

import com.liuhuachao.springbootjwt.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JWT 过滤器配置信息
 * @author liuhuachao
 * @date 2022/1/17
 */
@Configuration
public class JwtFilterConfiguration {

	@Bean
	public FilterRegistrationBean RegisterJwtFilter() {

		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		//注入过滤器
		registrationBean.setFilter(new JwtFilter());
		//拦截规则
		registrationBean.addUrlPatterns("/api/*");
		//忽略过滤
		registrationBean.addInitParameter("excludeUrls","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,*/login");
		//过滤器名称
		registrationBean.setName("jwtFilter");
		// 优先级 数值越小优先级越高
		registrationBean.setOrder(1);

		return registrationBean;
	}

}
