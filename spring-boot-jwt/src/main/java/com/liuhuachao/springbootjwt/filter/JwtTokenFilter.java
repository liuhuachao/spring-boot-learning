package com.liuhuachao.springbootjwt.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liuhuachao.springbootjwt.util.JwtUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * JWT 过滤器
 * @author liuhuachao
 * @date 2022/1/14
 */
public class JwtTokenFilter extends OncePerRequestFilter {
	private final String USER_NAME_KEY = "USER_NAME";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		try {
			String token = request.getHeader("Authorization");
			Map map = JwtUtils.checkToken(token);
			request.setAttribute(USER_NAME_KEY, map.get(USER_NAME_KEY));
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
			return;
		}

		filterChain.doFilter(request,response);
	}
}
