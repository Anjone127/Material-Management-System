package cn.edu.zucc.anjone.mrp.system.model;

import java.io.Serializable;

import cn.edu.zucc.anjone.mrp.util.PageRequest;

public class User extends PageRequest implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	private String id;
	private String userId;
	private String userName;
	private String userPassword;
	private String userPasswordSalt;
	private String userRoleId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
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
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserPasswordSalt() {
		return userPasswordSalt;
	}
	public void setUserPasswordSalt(String userPasswordSalt) {
		this.userPasswordSalt = userPasswordSalt;
	}
	public String getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}
	
	
	
}
