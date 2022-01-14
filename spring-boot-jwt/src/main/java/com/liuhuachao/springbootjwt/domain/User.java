package com.liuhuachao.springbootjwt.domain;

/**
 * @author liuhuachao
 * @date 2022/1/14
 */
public class User {

	/**
	 * 构造函数
	 * @param id
	 * @param userName
	 */
	public User(String id,String userName){
		this.id = id;
		this.userName = userName;
	}

	/**
	 * ID
	 */
	private String id;

	/**
	 * 用户名
	 */
	private String userName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
