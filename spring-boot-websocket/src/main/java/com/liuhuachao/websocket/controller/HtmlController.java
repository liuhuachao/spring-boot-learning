package com.liuhuachao.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HtmlController
 * @author liuhuachao
 * @date 2021/11/26
 */
@Controller
@RequestMapping(value = "api")
public class HtmlController {
	@GetMapping(value = "/html")
	public String getAPIHtml() {
		return "redirect:/html/WebSocketClient.html";
	}

	@GetMapping(value = "/websocket")
	public String getWebSocketHtml() {
		return "forward:/html/WebSocketClient.html";
	}
}
