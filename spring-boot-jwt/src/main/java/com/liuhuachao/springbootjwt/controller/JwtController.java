package com.liuhuachao.springbootjwt.controller;

import java.util.HashMap;
import java.util.Map;

import com.liuhuachao.springbootjwt.domain.User;
import com.liuhuachao.springbootjwt.util.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * JWT Controller
 * @author liuhuachao
 * @date 2022/1/14
 */
@RestController
@RequestMapping("api")
public class JwtController {
	private final String USER_NAME_KEY = "USER_NAME";

	@GetMapping("/test")
	public Object hellWorld(@RequestAttribute(value = USER_NAME_KEY)  String userName) {
		return "Welcome! Your USER_NAME : " + userName;
	}

	@PostMapping("/login")
	public Object login(String userName, String password) {
		if(isValidPassword(userName,password)) {
			// 将用户名传入并生成jwt
			Map<String,Object> map = new HashMap<>();
			map.put(USER_NAME_KEY, userName);

			String token = JwtUtils.createToken(map,new User("001","liuhuachao","BSG"),3600_000L);

			JwtUtils.saveCookie(token);

			// 将jwt返回给客户端
			return new HashMap<String,String>(){{
				put("token", token);
			}};
		}else {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}

	}

	/**
	 * 验证密码是否正确，模拟
	 */
	private boolean isValidPassword(String name, String password) {
		return "admin".equals(name) && "888888".equals(password);
	}
}
