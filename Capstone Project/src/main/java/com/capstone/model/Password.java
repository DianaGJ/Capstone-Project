package com.capstone.model;

public class Password extends StoredItem {
	private String website;
	private String password;
	
	public Password(String name, String description, String website, String password) {
		super(name, description);
		
		this.website = website;
		this.password = password;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
