package com.liuhuachao.springbootaop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liuhuachao
 * @date 2021/11/26
 */
@Controller
@RequestMapping("api")
public class AOPController {

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
