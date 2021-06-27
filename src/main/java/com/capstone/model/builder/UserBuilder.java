package com.capstone.model.builder;

import java.util.List;
import java.util.UUID;

import com.capstone.model.StoredPassword;
import com.capstone.model.User;

public final class UserBuilder {
	private static UserBuilder instance = new UserBuilder();
	
	public static synchronized UserBuilder getInstance() {
		return instance;
	}
	
	private UserBuilder() {
		
	}
	
	private UUID id;
	private String username;
	private String password;
	private String email;
	private List<StoredPassword> items;
	
	
	public UserBuilder setId(UUID id) {
		this.id = id;
		return this;
	}
	
	public UserBuilder setUsername(String username) {
		this.username = username;
		return this;
	}
	
	public UserBuilder setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public UserBuilder setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public UserBuilder withItems(List<StoredPassword> items) {
		this.items = items;		
		return this;
	}
	
	public User build() {
		return new User(id, username, password, email, items);
	}
}
