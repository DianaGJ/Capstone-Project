package com.capstone.model.builder;

import com.capstone.model.Password;
import com.capstone.model.StoredItem;

public class PasswordBuilder extends StoredItemBuilder {
	private String website;
	private String password;
	
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public StoredItem build() {
		return new Password(getId(), getName(), getDescription(), null, null, website, password);
	}
}
