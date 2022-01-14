package com.liuhuachao.springbootjwt.util;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.crypto.SecretKey;

import com.liuhuachao.springbootjwt.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

/**
 * JWT 工具类
 * @author liuhuachao
 * @date 2022/1/14
 */
public class JwtUtils {

	/**
	 * 到期毫秒数
	 */
	@Value("${spring.boot.jwt.validityInMilliseconds:3600000}")
	private long expirationMilliseconds = 3600000;

	/**
	 * 秘钥
	 */
	@Value("${spring.boot.jwt.issuer:liuhuachao}")
	private String issuer;

	/**
	 * 加密方式
	 */
	private static final SecretKey SECRETKEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	/**
	 * 创建 Token
	 * @author liuhuachao
	 * @date 2022/1/14 14:57
	 * @param payloadMap
	 * @param user
	 * @return java.lang.String
	 */
	public static String createToken(Map<String,Object> payloadMap, User user, Long expirationMilliseconds) {
		Date now  = new Date(System.currentTimeMillis());
		Date expirationTime = new Date(now.getTime() + expirationMilliseconds);

		JwtBuilder jwtBuilder = Jwts.builder()
				.setId(UUID.randomUUID().toString())
				// 设置签发者
				.setIssuer("liuhuachao")
				// 设置签发时间
				.setIssuedAt(now)
				// 设置过期时间
				.setExpiration(expirationTime)
				// 设置项目
				.setSubject(user.getUserName())
				// 设置签名
				.signWith(SECRETKEY)
				// 设置自定义数据
				.setClaims(payloadMap);

		String token = jwtBuilder.compact();

		return token;
	}

	/**
	 * 校验 Token
	 * @author liuhuachao
	 * @date 2022/1/14 16:09
	 * @param token
	 */
	public static Map checkToken(String token) {
		try {
			final Claims claims = parseToken(token);
			return claims;
		} catch (ExpiredJwtException e) {
			throw new JwtException("Token 过期失效：" + e.getMessage());
		} catch (JwtException | IllegalStateException e) {
			throw new JwtException("Token 验证失败：" + e.getMessage());
		}
	}

	/**
	 * 解析 Token
	 * @param token
	 * @return
	 */
	public static Claims parseToken(String token) {
		JwtParser parser = Jwts.parserBuilder().setSigningKey(SECRETKEY).build();
		Claims result = parser.parseClaimsJws(token).getBody();
		return result;
	}

}
