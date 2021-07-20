package com.capstone.model;

public class UserBuilder {

	private int id;
	private String username;
	private String email;
	private String password;
	private boolean isVerified;
	private String verificationCode;

	public User build() {
		return new User(id, username, email, password, isVerified, verificationCode);
	}

	public UserBuilder setId(int id) {
		this.id = id;
		return this;
	}

	public UserBuilder setUsername(String username) {
		this.username = username;
		return this;
	}

	public UserBuilder setEmail(String email) {
		this.email = email;
		return this;
	}

	public UserBuilder setPassword(String password) {
		this.password = password;
		return this;
	}

	public UserBuilder setIsVerified(boolean isVerified) {
		this.isVerified = isVerified;
		return this;
	}

	public UserBuilder setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
		return this;
	}

}
