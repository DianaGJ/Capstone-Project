package com.capstone.model;

import java.util.Date;
import java.util.UUID;

public class Password extends StoredItem {
	private String website;
	private String password;
	
	public Password(UUID id, String name, String description, Date created, Date modified, String website, String password) {
		super(id, name, description, created, modified);
		
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
	
	@Override
	public String getItemTypeCode() {
		return "PA";
	}
}
