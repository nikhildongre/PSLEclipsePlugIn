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
	String url;
	boolean isUrlAccessible;
	boolean isAuthorised;
	List<RepositoryFile>  repositoryFiles;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isUrlAccessible() {
		return isUrlAccessible;
	}

	public void setUrlAccessible(boolean isUrlAccessible) {
		this.isUrlAccessible = isUrlAccessible;
	}

	public boolean isAuthorised() {
		return isAuthorised;
	}

	public void setAuthorised(boolean isAuthorised) {
		this.isAuthorised = isAuthorised;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<RepositoryFile> getRepositoryFiles() {
		return repositoryFiles;
	}

	public void setRepositoryFiles(List<RepositoryFile> repositoryFiles) {
		this.repositoryFiles = repositoryFiles;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", url=" + url
				+ ", isUrlAccessible=" + isUrlAccessible + ", isAuthorised="
				+ isAuthorised + "]";
	}

}
