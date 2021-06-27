package com.capstone.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
	private UUID id;
	private String username;
	private String password;
	private String email;
	private boolean verified;
	private List<StoredPassword> items;
	
	public User(UUID id, String username, String password, String email) {
		this(id, username, password, email, new ArrayList<StoredPassword>());
	}
	
	public User(UUID id, String username, String password, String email, List<StoredPassword> items) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.items = items;
	}
	
	public UUID getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isVerified() {
		return verified;
	}
	
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	
	public void store(StoredPassword item) {
		items.add(item);
	}
	
	public void delete(StoredPassword item) {
		int index = items.indexOf(item);
		items.remove(index);
	}
	
	public List<StoredPassword> getItems() {
		return items;
	}
}
