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
	public User(String id,String userName,String organization){
		this.id = id;
		this.userName = userName;
		this.organization = organization;
	}

	/**
	 * ID
	 */
	private String id;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 所在组织
	 */
	private String organization;

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

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

}
