package com.capstone.model;

public final class UserBuilder {
	private long id;
	private String username;
	private String password;
	private String email;
	
	public UserBuilder setId(long id) {
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
	
	public User build() {
		return new User(id, username, password, email);
	}
}
