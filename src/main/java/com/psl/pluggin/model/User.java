/**
 * 
 */
package com.psl.pluggin.model;

import java.util.List;

/**
 * @author vishal_gupta1
 *
 */
public class User {
	
	String userName;
	String password;
	boolean isAccessible;
	boolean isAuthorised;
	List<String> allowedurls;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAccessible() {
		return isAccessible;
	}
	public void setAccessible(boolean isAccessible) {
		this.isAccessible = isAccessible;
	}
	public boolean isAuthorised() {
		return isAuthorised;
	}
	public void setAuthorised(boolean isAuthorised) {
		this.isAuthorised = isAuthorised;
	}
	public List<String> getAllowedurls() {
		return allowedurls;
	}
	public void setAllowedurls(List<String> allowedurls) {
		this.allowedurls = allowedurls;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password
				+ ", isAccessible=" + isAccessible + ", isAuthorised="
				+ isAuthorised + ", allowedurls=" + allowedurls + "]";
	}
	
	

}
