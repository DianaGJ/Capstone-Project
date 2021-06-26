package com.capstone.model.builder;

import java.util.UUID;

import com.capstone.model.StoredPassword;

public class StoredPasswordBuilder {
	private String name;
	private String description;
    private String website;
	private String password;
	
	public StoredPasswordBuilder setName(String name) {
		this.name = name;
		return this;
	}
	
	public StoredPasswordBuilder setDescription(String description) {
		this.description = description;
		return this;
	}
    
    public StoredPasswordBuilder setWebsite(String website) {
		this.website = website;
		return this;
	}
	
	public StoredPasswordBuilder setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public StoredPassword build() {
		return new StoredPassword(UUID.randomUUID(), name, description, null, null, website, password);
	}
}
