package com.liuhuachao.webservice;

import javax.xml.ws.Endpoint;

import com.liuhuachao.webservice.server.impl.JwsWebServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot 启动类
 * @author liuhuachao
 * @date 2022/1/24 15:44
 * @return null
 */
@SpringBootApplication
public class WebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebserviceApplication.class, args);
	}

}
