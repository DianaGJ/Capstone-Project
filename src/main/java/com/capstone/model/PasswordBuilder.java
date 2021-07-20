package com.capstone.model;

public class PasswordBuilder {

	private int id;
	private String website;
	private String websiteUser;
	private int userId;
	private String password;

	public Password build() {

		return new Password(id, website, websiteUser, userId, password);
	}

	public PasswordBuilder setId(int id) {
		this.id = id;
		return this;
	}

	public PasswordBuilder setWebsite(String website) {
		this.website = website;
		return this;
	}

	public PasswordBuilder setUserId(int userId) {
		this.userId = userId;
		return this;
	}

	public PasswordBuilder setPassword(String password) {
		this.password = password;
		return this;

	}

}
