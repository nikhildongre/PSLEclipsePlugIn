package com.psl.pluggin.model;

import java.util.Date;


public class Audit {
	String uid;
	String uname;
	String pwd;
	String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	Date createdDate;
	Date updatedDate;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getCreatedDate() {
		return new Date();
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return new Date();
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	@Override
	public String toString() {
		return "Audit [uid=" + uid + ", uname=" + uname + ", pwd=" + pwd
				+ ", url=" + url + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + "]";
	}
	
}
