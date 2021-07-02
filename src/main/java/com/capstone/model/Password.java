package com.capstone.model;

public class Password {
	
	
	private int id;
	private String website;
	private String websiteUser;
	private int userId;
	private String password;
	
	public Password(String website,String websiteUser, int userId, String password) {

		this.website = website;
		this.websiteUser= websiteUser;
		this.userId = userId;
		this.password = password;
	}
	public Password(int id,String website, int userId, String password) {

		this.id = id;
		this.website = website;
		this.userId = userId;
		this.password = password;
	}
	public Password() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userid) {
		this.userId = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getWebsiteUser() {
		return websiteUser;
	}
	public void setWebsiteUser(String websiteUser) {
		this.websiteUser = websiteUser;
	}
	
	

}
