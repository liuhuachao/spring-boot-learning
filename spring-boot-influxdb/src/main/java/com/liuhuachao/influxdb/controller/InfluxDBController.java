package com.liuhuachao.influxdb.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * InfluxDB 控制器页面
 * @author liuhuachao
 * @date 2021/12/7
 */
@RestController
@RequestMapping("influxdb")
public class InfluxDBController {

	/**
	 * GET
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/get")
	public String get(@RequestParam String json) {
		return json;
	}

	/**
	 * POST
	 * @param json
	 * @return
	 */
	@PostMapping(value = "/post", consumes = "application/json;charset=UTF-8")
	public String post(@RequestBody String json) {
		return json;
	}

}
