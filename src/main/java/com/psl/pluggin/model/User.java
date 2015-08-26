/**
 * 
 */
package com.psl.pluggin.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;

/**
 * @author vishal_gupta1
 *
 */
@XmlRootElement(name="user")
public class User {
	
	String userName;
	String password;
	String url;
	boolean isAccessible;
	boolean isAuthorised;
	List<String> allowedurls;
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@XmlElement
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@XmlElement
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@XmlElement
	public boolean isAccessible() {
		return isAccessible;
	}
	@XmlElement
	public void setAccessible(boolean isAccessible) {
		this.isAccessible = isAccessible;
	}
	@XmlElement
	public boolean isAuthorised() {
		return isAuthorised;
	}
	public void setAuthorised(boolean isAuthorised) {
		this.isAuthorised = isAuthorised;
	}
	@XmlElement
	public List<String> getAllowedurls() {
		return allowedurls;
	}
	public void setAllowedurls(List<String> allowedurls) {
		this.allowedurls = allowedurls;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password+",Url= "+url
				+ ", isAccessible=" + isAccessible + ", isAuthorised="
				+ isAuthorised + ", allowedurls=" + allowedurls + "]";
	}
	
	

}
