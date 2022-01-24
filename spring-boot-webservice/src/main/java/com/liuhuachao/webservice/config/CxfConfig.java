package com.liuhuachao.webservice.config;

import javax.xml.ws.Endpoint;

import com.liuhuachao.webservice.server.ErpWebService;
import com.liuhuachao.webservice.server.JwsWebService;
import com.liuhuachao.webservice.server.impl.ErpWebServiceImpl;
import com.liuhuachao.webservice.server.impl.JwsWebServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CxfConfig 配置类
 * @author liuhuachao
 * @date 2022/1/24
 */
@Configuration
public class CxfConfig {

	@Value("${webservice.erp.url}")
	private String webServiceErpUrl;

	@Bean
	public ServletRegistrationBean cxfServletRegistrationBean() {
		return new ServletRegistrationBean(new CXFServlet(), "/webservice/*");
	}

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}

	@Bean
	public JwsWebService jwsWebService() {
		return new JwsWebServiceImpl();
	}

	@Bean
	public ErpWebService erpWebService() {
		return new ErpWebServiceImpl();
	}

	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), jwsWebService());
		// endpoint.getInInterceptors().add(new AuthInterceptor()); //添加校验拦截器
		endpoint.publish("/jws");

		EndpointImpl erpEndpoint = new EndpointImpl(springBus(), erpWebService());
		erpEndpoint.publish(webServiceErpUrl);

		return endpoint;
	}

}
